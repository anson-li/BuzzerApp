package ca.ansonli.www.buzzerapp.util;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import ca.ansonli.www.buzzerapp.R;

/**
 * Created by ansonli on 2015-09-29.
 */
public class statsLogic {

    public ArrayList<Integer> minimumTime(String[][] data) {
        // have to include last 10 and last 100 times!

        boolean minBool = false;
        int counter = 0;
        ArrayList<Integer> returnSet = new ArrayList<>();
        int minimumVal = 100000000;
        int minimumValTen = 100000000;
        int minimumValHundred = 100000000;

        if (data.length > 0 && data[0].length > 1) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("1")) {
                    if (minimumVal > Integer.parseInt(data[i][1])) {
                        minimumVal = Integer.parseInt(data[i][1]);
                    }
                    if (counter < 10) {
                        if (minimumValTen > Integer.parseInt(data[i][1])) {
                            minimumValTen = Integer.parseInt(data[i][1]);
                        }
                    }
                    if (counter < 100) {
                        if (minimumValHundred > Integer.parseInt(data[i][1])) {
                            minimumValHundred = Integer.parseInt(data[i][1]);
                        }
                    }
                    minBool = true;
                    counter = counter + 1;
                }
            }

            int seconds = minimumVal / 1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            minimumVal = minimumVal % 1000;

            if (minBool) {
                returnSet.add(1);
                returnSet.add(minutes);
                returnSet.add(seconds);
                returnSet.add(minimumVal);
                returnSet.add(minimumValTen);
                returnSet.add(minimumValHundred);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }
        return returnSet;
    }

    public ArrayList<Integer> maximumTime(String[][] data) {
        boolean maxBool = false;
        ArrayList<Integer> returnSet = new ArrayList<Integer>();

        int counter = 0;
        int maximumVal = 0;
        int maximumValTen = 0;
        int maximumValHundred = 0;
        if (data.length > 0 && data[0].length > 1) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("1")) {
                    if (maximumVal < Integer.parseInt(data[i][1])) {
                        maximumVal = Integer.parseInt(data[i][1]);
                    }
                    if (counter < 10) {
                        if (maximumValTen < Integer.parseInt(data[i][1])) {
                            maximumValTen = Integer.parseInt(data[i][1]);
                        }
                    }
                    if (counter < 100) {
                        if (maximumValHundred < Integer.parseInt(data[i][1])) {
                            maximumValHundred = Integer.parseInt(data[i][1]);
                        }
                    }
                    counter = counter + 1;
                    maxBool = true;
                }
            }

            int seconds = maximumVal / 1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            maximumVal = maximumVal % 1000;
            if (maxBool) {
                returnSet.add(1);
                returnSet.add(minutes);
                returnSet.add(seconds);
                returnSet.add(maximumVal);
                returnSet.add(maximumValTen);
                returnSet.add(maximumValHundred);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }
        return returnSet;
    }

    public ArrayList<Integer> meanTime(String[][] data) {
        ArrayList<Integer> returnSet = new ArrayList<>();
        boolean meanBool = false;

        int meanVal = 0;
        int meanValTen = 0;
        int meanValHundred = 0;
        int counter = 0;

        ArrayList meanArray = new ArrayList();

        if (data.length > 0 && data[0].length > 1) {

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("1")) {
                    meanVal = meanVal + Integer.parseInt(data[i][1]);
                    meanArray.add(Integer.parseInt(data[i][1]));
                    if (counter < 10) {
                        meanValTen = meanValTen + Integer.parseInt(data[i][1]);
                    }
                    if (counter < 100) {
                        meanValHundred = meanValHundred + Integer.parseInt(data[i][1]);
                    }
                    meanBool = true;
                    counter = counter + 1;
                }
            }
            if (meanArray.size() > 0) {
                meanVal = meanVal / meanArray.size();
                if (counter < 10) {
                    meanValTen = meanValTen / counter;
                } else {
                    meanValTen = meanValTen / 10;
                }
                if (counter < 100) {
                    meanValHundred = meanValHundred / counter;
                } else {
                    meanValHundred = meanValHundred / 100;
                }
            }
            int seconds = meanVal / 1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            meanVal = meanVal % 1000;
            if (meanBool) {
                returnSet.add(1);
                returnSet.add(minutes);
                returnSet.add(seconds);
                returnSet.add(meanVal);
                returnSet.add(meanValTen);
                returnSet.add(meanValHundred);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }

        return returnSet;
    }

    public ArrayList<Integer> medianTime(String[][] data) {
        boolean medianBool = false;
        ArrayList medianArray = new ArrayList();
        ArrayList medianArrayTen = new ArrayList();
        ArrayList medianArrayHundred = new ArrayList();
        int counter = 0;

        int medianVal = 0;
        int medianValTen = 0;
        int medianValHundred = 0;

        ArrayList<Integer> returnSet = new ArrayList<>();

        if (data.length > 0 && data[0].length > 1) {

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("1")) {
                    medianArray.add(Integer.parseInt(data[i][1]));
                    if (counter < 10) {
                        medianArrayTen.add(Integer.parseInt(data[i][1]));
                    }
                    if (counter < 100) {
                        medianArrayHundred.add(Integer.parseInt(data[i][1]));
                    }
                    counter = counter + 1;
                    medianBool = true;
                }
            }
            Collections.sort(medianArray);
            Collections.sort(medianArrayTen);
            Collections.sort(medianArrayHundred);

            // snippet taken from http://code.hammerpig.com/simple-compute-median-java.html
            if (medianArray.size() > 0) {
                if (medianArray.size() % 2 == 0) { // even, take the middle two and find the average of them
                    int lower = (Integer) medianArray.get(medianArray.size() / 2 - 1);
                    int upper = (Integer) medianArray.get(medianArray.size() / 2);
                    medianVal = ((lower + upper) / 2);
                } else { // odd
                    medianVal = (Integer) medianArray.get(medianArray.size() / 2);
                }
            }

            if (medianArrayTen.size() > 0) {
                if (medianArrayTen.size() % 2 == 0) { // even, take the middle two and find the average of them
                    int lower = (Integer) medianArrayTen.get(medianArrayTen.size() / 2 - 1);
                    int upper = (Integer) medianArrayTen.get(medianArrayTen.size() / 2);
                    medianValTen = ((lower + upper) / 2);
                } else { // odd
                    medianValTen = (Integer) medianArray.get(medianArrayTen.size() / 2);
                }
            }

            if (medianArrayHundred.size() > 0) {
                if (medianArrayHundred.size() % 2 == 0) { // even, take the middle two and find the average of them
                    int lower = (Integer) medianArrayHundred.get(medianArrayHundred.size() / 2 - 1);
                    int upper = (Integer) medianArrayHundred.get(medianArrayHundred.size() / 2);
                    medianValHundred = ((lower + upper) / 2);
                } else { // odd
                    medianValHundred = (Integer) medianArray.get(medianArrayHundred.size() / 2);
                }
            }

            int seconds = medianVal / 1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            medianVal = medianVal % 1000;
            if (medianBool) {
                returnSet.add(1);
                returnSet.add(minutes);
                returnSet.add(seconds);
                returnSet.add(medianVal);
                returnSet.add(medianValTen);
                returnSet.add(medianValHundred);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }

        return returnSet;

    }

    public ArrayList<Integer> twoPlayer (String[][] data) {

        ArrayList<Integer> returnSet = new ArrayList<>();
        boolean twoBool = false;

        int playwin1 = 0;
        int playwin2 = 0;

        if (data.length > 0 && data[0].length > 1) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("2")) {
                    twoBool = true;
                    if (data[i][1].equals("1")) {
                        playwin1 = playwin1 + 1;
                    }
                    else if (data[i][1].equals("2")) {
                        playwin2 = playwin2 + 1;
                    }
                }
            }
            if (twoBool) {
                returnSet.add(1);
                returnSet.add(playwin1);
                returnSet.add(playwin2);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }

        return returnSet;
    }

    public ArrayList<Integer> threePlayer (String[][] data) {

        ArrayList<Integer> returnSet = new ArrayList<>();
        boolean threeBool = false;

        int playwin1 = 0;
        int playwin2 = 0;
        int playwin3 = 0;

        if (data.length > 0 && data[0].length > 1) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("3")) {
                    threeBool = true;
                    if (data[i][1].equals("1")) {
                        playwin1 = playwin1 + 1;
                    } else if (data[i][1].equals("2")) {
                        playwin2 = playwin2 + 1;
                    } else if (data[i][1].equals("3")) {
                        playwin3 = playwin3 + 1;
                    }
                }
            }
            if (threeBool) {
                returnSet.add(1);
                returnSet.add(playwin1);
                returnSet.add(playwin2);
                returnSet.add(playwin3);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }

        return returnSet;
    }

    public ArrayList<Integer> fourPlayer(String[][] data) {

        ArrayList<Integer> returnSet = new ArrayList<>();
        boolean fourBool = false;

        int playwin1 = 0;
        int playwin2 = 0;
        int playwin3 = 0;
        int playwin4 = 0;

        if (data.length > 0 && data[0].length > 1) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals("4")) {
                    fourBool = true;
                    if (data[i][1].equals("1")) {
                        playwin1 = playwin1 + 1;
                    } else if (data[i][1].equals("2")) {
                        playwin2 = playwin2 + 1;
                    } else if (data[i][1].equals("3")) {
                        playwin3 = playwin3 + 1;
                    } else if (data[i][1].equals("4")) {
                        playwin4 = playwin4 + 1;
                    }
                }
            }
            if (fourBool) {
                returnSet.add(1);
                returnSet.add(playwin1);
                returnSet.add(playwin2);
                returnSet.add(playwin3);
                returnSet.add(playwin4);
            } else {
                returnSet.add(0);
            }
        } else {
            returnSet.add(0);
        }

        return returnSet;
    }
}