package ca.ansonli.www.buzzerapp.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import ca.ansonli.www.buzzerapp.FourActivity;
import ca.ansonli.www.buzzerapp.SelectPlayersActivity;
import ca.ansonli.www.buzzerapp.SingleActivity;
import ca.ansonli.www.buzzerapp.StatsActivity;
import ca.ansonli.www.buzzerapp.ThreeActivity;
import ca.ansonli.www.buzzerapp.TwoActivity;

/**
 * Created by ansonli on 2015-09-29.
 */
public class ActivitySwitch {

    // snippet taken from http://www.learn2crack.com/2013/09/android-switching-between-activities-example.html

    public void switchToSingle(Context cntx) {
        Intent myIntent = new Intent(cntx, SingleActivity.class);
        Toast.makeText(cntx, "Moving to single-player activity...", Toast.LENGTH_SHORT).show();
        cntx.startActivity(myIntent);
    }

    public void switchToStats(Context cntx) {
        Intent myIntent = new Intent(cntx, StatsActivity.class);
        Toast.makeText(cntx, "Moving to statistics page...", Toast.LENGTH_SHORT).show();
        cntx.startActivity(myIntent);
    }

    public void switchToMulti(Context cntx) {
        Intent myIntent = new Intent(cntx, SelectPlayersActivity.class);
        Toast.makeText(cntx, "Starting Multiplayer Game...", Toast.LENGTH_SHORT).show();
        cntx.startActivity(myIntent);
    }

    public void switchTwo(Context cntx) {
        Intent myIntent = new Intent(cntx, TwoActivity.class);
        Toast.makeText(cntx, "Starting Two-Player Game...", Toast.LENGTH_SHORT).show();
        cntx.startActivity(myIntent);
    }

    public void switchThree(Context cntx) {
        Intent myIntent = new Intent(cntx, ThreeActivity.class);
        Toast.makeText(cntx, "Starting Three-Player Game...", Toast.LENGTH_SHORT).show();
        cntx.startActivity(myIntent);
    }

    public void switchFour(Context cntx) {
        Intent myIntent = new Intent(cntx, FourActivity.class);
        Toast.makeText(cntx, "Starting Four-Player Game...", Toast.LENGTH_SHORT).show();
        cntx.startActivity(myIntent);
    }

}
