package ca.ansonli.www.buzzerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ca.ansonli.www.buzzerapp.util.ActivitySwitch;
import ca.ansonli.www.buzzerapp.util.EmailParser;
import ca.ansonli.www.buzzerapp.util.statsLogic;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StatsActivity extends AppCompatActivity {

    public ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private View mContentView;
    private View mControlsView;
    private boolean mVisible;

    private ListView statsList;
    private ArrayAdapter<String> adapter;

    private String emailData;
    final EmailParser ep = new EmailParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        statsList = (ListView)findViewById(R.id.statsList);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        statsList.setAdapter(adapter);

        readData();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivitySwitch as = new ActivitySwitch();
                if (position == 2) {
                    as.switchToStats(StatsActivity.this);
                } else if (position == 1) {
                    as.switchToMulti(StatsActivity.this);
                } else if (position == 0) {
                    as.switchToSingle(StatsActivity.this);
                }
            }
        });

        setupDrawer();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    private void readData() {
        // code taken from Agarwal Shankar from http://stackoverflow.com/questions/10860357/is-it-possible-to-read-a-file-from-internal-storage-android
        int ch;
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis;
        try {
            fis = this.openFileInput("buttonTimes.txt");
            try {
                while( (ch = fis.read()) != -1)
                    fileContent.append((char)ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String data = new String(fileContent);
        parseData(data);
    }

    private void parseData(String data) {

        // code taken from polygenelubricants at http://stackoverflow.com/questions/2786777/convert-string-into-two-dimensional-string-array-in-java
        String[] rows = data.split(",");

        String[][] matrix = new String[rows.length][];
        int r = 0;
        for (String row : rows) {
            matrix[r++] = row.split("\\|");
        }

        adapter.clear();
        adapter.notifyDataSetChanged();

        emailData = "Stats Generated for BuzzerApp:\n";

        minimumTime(matrix);
        maximumTime(matrix);
        meanTime(matrix);
        medianTime(matrix);
        twoPlayerStats(matrix);
        threePlayerStats(matrix);
        fourPlayerStats(matrix);

        adapter.notifyDataSetChanged();

    }

    private void minimumTime(String[][] data) {
        statsLogic sl = new statsLogic();
        ArrayList<Integer> returnSet = sl.minimumTime(data);
        if (returnSet.get(0) == 1) {

            int minutes = returnSet.get(1);
            int seconds = returnSet.get(2);
            int minimumVal = returnSet.get(3);
            int minimumValTen = returnSet.get(4);
            int minimumValHundred = returnSet.get(5);

            adapter.add(String.format("Minimum Time Recorded - Lifetime: %d:%02d:%03d", minutes, seconds, minimumVal));
            adapter.add(String.format("     & Last 10 Runs: %d:%02d:%03d", ((minimumValTen / 60) / 1000), ((minimumValTen / 1000) % 60), (minimumValTen % 1000)));
            adapter.add(String.format("     & Last 100 Runs: %d:%02d:%03d", ((minimumValHundred / 60) / 1000), ((minimumValHundred / 1000) % 60), (minimumValHundred % 1000)));

            emailData = ep.minimumTime(emailData, minutes, seconds, minimumVal, minimumValTen, minimumValHundred);

        } else {
            adapter.add("Minimum Time recorded: No data found.");
            emailData = ep.minimumTimeFail(emailData);
        }
    }

    //min, max, mean, median
    private void maximumTime(String[][] data) {

        statsLogic sl = new statsLogic();

        ArrayList<Integer> returnSet = sl.maximumTime(data);
        if (returnSet.get(0) == 1) {

            int minutes = returnSet.get(1);
            int seconds = returnSet.get(2);
            int maximumVal = returnSet.get(3);
            int maximumValTen = returnSet.get(4);
            int maximumValHundred = returnSet.get(5);

            adapter.add(String.format("Maximum Time Recorded - Lifetime: %d:%02d:%03d", minutes, seconds, maximumVal));
            adapter.add(String.format("     & Last 10 Runs: %d:%02d:%03d", ((maximumValTen / 60) / 1000), ((maximumValTen / 1000) % 60), (maximumValTen % 1000)));
            adapter.add(String.format("     & Last 100 Runs: %d:%02d:%03d", ((maximumValHundred / 60) / 1000), ((maximumValHundred / 1000) % 60), (maximumValHundred % 1000)));

            emailData = ep.maximumTime(emailData, minutes, seconds, maximumVal, maximumValTen, maximumValHundred);

        } else {
            adapter.add("Maximum Time recorded: No data found.");
            emailData = ep.maximumTimeFail(emailData);
        }
    }

    //min, max, mean, median
    private void meanTime(String[][] data) {

        statsLogic sl = new statsLogic();

        ArrayList<Integer> returnSet = sl.meanTime(data);
        if (returnSet.get(0) == 1) {

            int minutes = returnSet.get(1);
            int seconds = returnSet.get(2);
            int meanVal = returnSet.get(3);
            int meanValTen = returnSet.get(4);
            int meanValHundred = returnSet.get(5);

            adapter.add(String.format("Mean Time Recorded - Lifetime: %d:%02d:%03d", minutes, seconds, meanVal));
            adapter.add(String.format("     & Last 10 Runs: %d:%02d:%03d", ((meanValTen / 60) / 1000), ((meanValTen / 1000) % 60), (meanValTen % 1000)));
            adapter.add(String.format("     & Last 100 Runs: %d:%02d:%03d", ((meanValHundred / 60) / 1000), ((meanValHundred / 1000) % 60), (meanValHundred % 1000)));

            emailData = ep.meanTime(emailData, minutes, seconds, meanVal, meanValTen, meanValHundred);

        } else {
            adapter.add("Mean Time recorded: No data found.");
            emailData = ep.meanTimeFail(emailData);
        }
    }

    //min, max, mean, median
    private void medianTime(String[][] data) {

        statsLogic sl = new statsLogic();

        ArrayList<Integer> returnSet = sl.medianTime(data);
        if (returnSet.get(0) == 1) {

            int minutes = returnSet.get(1);
            int seconds = returnSet.get(2);
            int medianVal = returnSet.get(3);
            int medianValTen = returnSet.get(4);
            int medianValHundred = returnSet.get(5);

            adapter.add(String.format("Median Time Recorded - Lifetime: %d:%02d:%03d", minutes, seconds, medianVal));
            adapter.add(String.format("     & Last 10 Runs: %d:%02d:%03d", ((medianValTen / 60) / 1000), ((medianValTen / 1000) % 60), (medianValTen % 1000)));
            adapter.add(String.format("     & Last 100 Runs: %d:%02d:%03d", ((medianValHundred / 60) / 1000), ((medianValHundred / 1000) % 60), (medianValHundred % 1000)));

            emailData = ep.medianTime(emailData, minutes, seconds, medianVal, medianValTen, medianValHundred);

        } else {
            adapter.add("Median Time recorded: No data found.");
            emailData = ep.medianTimeFail(emailData);
        }
    }

    private void twoPlayerStats(String[][] data) {
        statsLogic sl = new statsLogic();

        ArrayList<Integer> returnSet = sl.twoPlayer(data);
        if (returnSet.get(0) == 1) {

            int playwin1 = returnSet.get(1);
            int playwin2 = returnSet.get(2);

            adapter.add(String.format("2P: Player 1 Wins: %d", playwin1));
            adapter.add(String.format("2P: Player 2 Wins: %d", playwin2));
            emailData = ep.twoPlayer(emailData, playwin1, playwin2);

        } else {
            adapter.add("2P: No data found." );
            emailData = ep.twoPlayerFail(emailData);
        }
    }

    private void threePlayerStats(String[][] data) {
        statsLogic sl = new statsLogic();

        ArrayList<Integer> returnSet = sl.threePlayer(data);
        if (returnSet.get(0) == 1) {

            int playwin1 = returnSet.get(1);
            int playwin2 = returnSet.get(2);
            int playwin3 = returnSet.get(3);

            adapter.add(String.format("3P: Player 1 Wins: %d", playwin1));
            adapter.add(String.format("3P: Player 2 Wins: %d", playwin2));
            adapter.add(String.format("3P: Player 3 Wins: %d", playwin3));
            emailData = ep.threePlayer(emailData, playwin1, playwin2, playwin3);

        } else {
            adapter.add("3P: No data found.");
            emailData = ep.threePlayerFail(emailData);

        }
    }

    private void fourPlayerStats(String[][] data) {
        statsLogic sl = new statsLogic();

        ArrayList<Integer> returnSet = sl.fourPlayer(data);
        if (returnSet.get(0) == 1) {

            int playwin1 = returnSet.get(1);
            int playwin2 = returnSet.get(2);
            int playwin3 = returnSet.get(3);
            int playwin4 = returnSet.get(4);

            adapter.add(String.format("4P: Player 1 Wins: %d", playwin1));
            adapter.add(String.format("4P: Player 2 Wins: %d", playwin2));
            adapter.add(String.format("4P: Player 3 Wins: %d", playwin3));
            adapter.add(String.format("4P: Player 4 Wins: %d", playwin4));
            emailData = ep.fourPlayer(emailData, playwin1, playwin2, playwin3, playwin4);

        } else {
            adapter.add("4P: No data found.");
            emailData = ep.fourPlayerFail(emailData);
        }
    }

    private void clearStats() {
        deleteFile("buttonTimes.txt");
        readData();
    }

    private void emailStats() {
        // code taken from http://developer.android.com/training/sharing/send.html
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Hey! Here are my BuzzerApp Statistics");
        sendIntent.putExtra(Intent.EXTRA_TEXT, emailData);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_clear) {
            clearStats();
            return true;
        }

        if (id == R.id.action_email) {
            emailStats();
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        readData();
    }


    private void addDrawerItems() {
        String[] osArray = { "Single Player", "Multiplayer", "Statistics" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StatsActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
