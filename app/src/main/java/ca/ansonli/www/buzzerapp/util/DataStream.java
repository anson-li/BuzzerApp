package ca.ansonli.www.buzzerapp.util;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by ansonli on 2015-09-28.
 */
public class DataStream {

    public void saveData(int noPlayers, int winnerId, Context cntx) {
        String fileName = "buttonTimes.txt";
        String content = String.format("%d|%d,", noPlayers, winnerId);

        FileOutputStream outputStream;
        try {
            outputStream = cntx.getApplicationContext().openFileOutput(fileName, cntx.MODE_APPEND | cntx.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
