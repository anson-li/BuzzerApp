package ca.ansonli.www.buzzerapp.util;

/**
 * Created by ansonli on 2015-09-29.
 */
public class EmailParser {

    public String minimumTime(String emailData, int minutes, int seconds, int minimumVal, int minimumValTen, int minimumValHundred) {
        emailData = emailData + String.format("Minimum Time Recorded: Lifetime:  %d:%02d:%03d\n", minutes, seconds, minimumVal);
        emailData = emailData + String.format("Minimum Time Recorded: Last 10 Runs: %d:%02d:%03d\n", ((minimumValTen / 60) / 1000), ((minimumValTen / 1000) % 60), (minimumValTen % 1000));
        emailData = emailData + String.format("Minimum Time Recorded: Last 100 Runs: %d:%02d:%03d\n", ((minimumValHundred / 60) / 1000), ((minimumValHundred / 1000) % 60), (minimumValHundred % 1000));
        return emailData;
    }

    public String minimumTimeFail(String emailData) {
        emailData = emailData + "Minimum Time recorded: No data found.\n";
        return emailData;
    }

    public String maximumTime(String emailData, int minutes, int seconds, int maximumVal, int maximumValTen, int maximumValHundred) {
        emailData = emailData + String.format("Maximum Time Recorded: Lifetime:  %d:%02d:%03d\n", minutes, seconds, maximumVal);
        emailData = emailData + String.format("Maximum Time Recorded: Last 10 Runs: %d:%02d:%03d\n", ((maximumValTen / 60) / 1000), ((maximumValTen / 1000) % 60), (maximumValTen % 1000));
        emailData = emailData + String.format("Maximum Time Recorded: Last 100 Runs: %d:%02d:%03d\n", ((maximumValHundred / 60) / 1000), ((maximumValHundred / 1000) % 60), (maximumValHundred % 1000));
        return emailData;
    }

    public String maximumTimeFail(String emailData) {
        emailData = emailData + "Maximum Time recorded: No data found.\n";
        return emailData;
    }

    public String meanTime(String emailData, int minutes, int seconds, int meanVal, int meanValTen, int meanValHundred) {
        emailData = emailData + String.format("Mean Time Recorded: Lifetime: %d:%02d:%03d\n", minutes, seconds, meanVal);
        emailData = emailData + String.format("Mean Time Recorded: Last 10 Runs: %d:%02d:%03d\n", ((meanValTen / 60) / 1000), ((meanValTen / 1000) % 60), (meanValTen % 1000));
        emailData = emailData + String.format("Mean Time Recorded: Last 100 Runs: %d:%02d:%03d\n", ((meanValHundred / 60) / 1000), ((meanValHundred / 1000) % 60), (meanValHundred % 1000));
        return emailData;
    }

    public String meanTimeFail(String emailData) {
        emailData = emailData + "Mean Time recorded: No data found.\n";
        return emailData;
    }

    public String medianTime(String emailData, int minutes, int seconds, int medianVal, int medianValTen, int medianValHundred) {
        emailData = emailData + String.format("Median Time Recorded: Lifetime: %d:%02d:%03d\n", minutes, seconds, medianVal);
        emailData = emailData + String.format("Median Time Recorded: Last 10 Runs: %d:%02d:%03d\n", ((medianValTen / 60) / 1000), ((medianValTen / 1000) % 60), (medianValTen % 1000));
        emailData = emailData + String.format("Median Time Recorded: Last 100 Runs: %d:%02d:%03d\n", ((medianValHundred / 60) / 1000), ((medianValHundred / 1000) % 60), (medianValHundred % 1000));
        return emailData;
    }

    public String medianTimeFail(String emailData) {
        emailData = emailData + "Median Time recorded: No data found.\n";
        return emailData;
    }

    public String twoPlayer(String emailData, int playwin1, int playwin2) {
        emailData = emailData + String.format("2P: Player 1 Wins: %d\n", playwin1);
        emailData = emailData + String.format("2P: Player 2 Wins: %d\n", playwin2);
        return emailData;
    }

    public String twoPlayerFail(String emailData) {
        emailData = emailData + "2P: No data found.\n";
        return emailData;
    }

    public String threePlayer(String emailData, int playwin1, int playwin2, int playwin3) {
        emailData = emailData + String.format("3P: Player 1 Wins: %d\n", playwin1);
        emailData = emailData + String.format("3P: Player 2 Wins: %d\n", playwin2);
        emailData = emailData + String.format("3P: Player 3 Wins: %d\n", playwin3);
        return emailData;
    }

    public String threePlayerFail(String emailData) {
        emailData = emailData + "3P: No data found.\n";
        return emailData;
    }

    public String fourPlayer(String emailData, int playwin1, int playwin2, int playwin3, int playwin4) {
        emailData = emailData + String.format("4P: Player 1 Wins: %d\n", playwin1);
        emailData = emailData + String.format("4P: Player 2 Wins: %d\n", playwin2);
        emailData = emailData + String.format("4P: Player 3 Wins: %d\n", playwin3);
        emailData = emailData + String.format("4P: Player 4 Wins: %d\n", playwin4);
        return emailData;
    }

    public String fourPlayerFail(String emailData) {
        emailData = emailData + "4P: No data found.\n";
        return emailData;
    }

}
