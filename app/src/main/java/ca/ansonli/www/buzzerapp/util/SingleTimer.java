package ca.ansonli.www.buzzerapp.util;

import android.content.Context;
import android.os.Handler;

import java.io.FileOutputStream;

/**
 * Created by ansonli on 2015-09-29.
 */
public class SingleTimer {

    public long startTime = 0;
    public long millis;
    public int seconds;
    public int minutes;

    public Handler timerHandler = new Handler();
    public Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            seconds = (int) (millis / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;

            timerHandler.postDelayed(this, 500);
        }
    };

    public void saveTime (Context cntx) {
        // code taken from Rishabh from http://codetheory.in/android-saving-files-on-internal-and-external-storage/
        String fileName = "buttonTimes.txt";
        String content = String.format("1|%d,", millis);

        FileOutputStream outputStream;
        try {
            outputStream = cntx.openFileOutput(fileName, Context.MODE_APPEND | Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
