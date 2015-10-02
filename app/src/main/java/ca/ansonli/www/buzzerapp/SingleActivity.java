package ca.ansonli.www.buzzerapp;

import ca.ansonli.www.buzzerapp.util.ActivitySwitch;
import ca.ansonli.www.buzzerapp.util.SingleTimer;
import ca.ansonli.www.buzzerapp.util.SystemUiHider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.io.FileOutputStream;
import java.util.Random;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SingleActivity extends AppCompatActivity {

    public SingleTimer st = new SingleTimer();
    final ActivitySwitch as = new ActivitySwitch();

    public ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    public boolean isGreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single);

        final Button button = (Button) findViewById(R.id.dummy_button);

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

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
                if (position == 2) {
                    as.switchToStats(SingleActivity.this);
                } else if (position == 1) {
                    as.switchToMulti(SingleActivity.this);
                } else if (position == 0) {
                    as.switchToSingle(SingleActivity.this);
                }
            }
        });

        setupDrawer();
        startTimer();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isGreen) {
                } else {
                    button.setBackgroundColor(Color.RED);
                    button.setTextColor(Color.WHITE);
                    button.setText(String.format("Congratulations, your recorded time is: %d:%02d:%02d. \nPlease wait a second and the game will restart.", st.minutes, st.seconds, st.millis % 1000));
                    isGreen = false;
                    st.saveTime(SingleActivity.this);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startTimer();
                        }
                    }, 2000);
                }

            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addDrawerItems() {
        String[] osArray = { "Single Player", "Multiplayer", "Statistics" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

    }

    public void startTimer () {
        final Button button = (Button) findViewById(R.id.dummy_button);
        button.setBackgroundColor(Color.RED);
        button.setTextColor(Color.WHITE);
        button.setText("DON'T CLICK ME YET!");
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SingleActivity.this);
        myAlertDialog.setTitle("Timer!");
        myAlertDialog.setMessage("Press the button once it changes color! The time at which the color changes will be recognised!");
        myAlertDialog.setCancelable(false);
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // using code by Dave.B from http://stackoverflow.com/questions/4597690/android-timer-how
                st.startTime = System.currentTimeMillis();
                st.timerHandler.postDelayed(st.timerRunnable, 0);
                // using code from trutheality from http://stackoverflow.com/questions/6741100/random-numbers-in-java-when-working-with-android
                Random rand = new Random();
                // code taken from Jim Blackler from http://stackoverflow.com/questions/5623578/android-delay-using-handler

                Runnable timerRunnable = new Runnable() {
                    public void run() {
                        isGreen = true;
                        button.setBackgroundColor(Color.GREEN);
                        button.setTextColor(Color.BLACK);
                        button.setText("TIME TIME TIME TIME TIME");
                        st.startTime = System.currentTimeMillis();
                    }
                };

                st.timerHandler.postDelayed(timerRunnable, rand.nextInt(1990) + 10);
            }
        });
        myAlertDialog.show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        // temporary hold on the timer value.
        final Button button = (Button) findViewById(R.id.dummy_button);
        button.setBackgroundColor(Color.RED);
        button.setTextColor(Color.WHITE);
        st.timerHandler.removeCallbacks(st.timerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        st.timerHandler.removeCallbacks(st.timerRunnable);
        startTimer();
        // restart the timer application
    }

}
