package ca.ansonli.www.buzzerapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by ansonli on 2015-09-28.
 */
public class AlertDlg {

    public void twoplayerone(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player one has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(2, 1, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void twoplayertwo(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player two has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(2, 2, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void threeplayerone(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player one has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(3, 1, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void threeplayertwo(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player two has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(3, 2, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void threeplayerthree(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player three has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(3, 3, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void fourplayerone(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player one has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(4, 1, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void fourplayertwo(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player two has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(4, 2, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void fourplayerthree(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player three has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(4, 3, cntx);
            }
        });
        myAlertDialog.show();
    }

    public void fourplayerfour(final Context cntx) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(cntx);
        final DataStream ds = new DataStream();
        myAlertDialog.setTitle("Result");
        myAlertDialog.setMessage("Player four has clicked first! \nPress OK to continue.");
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ds.saveData(4, 4, cntx);
            }
        });
        myAlertDialog.show();
    }

}
