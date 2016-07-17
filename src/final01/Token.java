package final01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Token {
    private List<String> startRed;
    private List<String> startBlue;
    private List<String> startGreen;
    private List<String> startYellow;
    private List<String> field;
    private List<String> destRed;
    private List<String> destBlue;
    private List<String> destGreen;
    private List<String> destYellow;

    public Token() {
        startRed = new ArrayList<>();
        startBlue = new ArrayList<>();
        startGreen = new ArrayList<>();
        startYellow = new ArrayList<>();
        field = new ArrayList<>();
        destRed = new ArrayList<>();
        destBlue = new ArrayList<>();
        destGreen = new ArrayList<>();
        destYellow = new ArrayList<>();
    }

    public String getRed() {
        String output = new String();

        for (String val : startRed) {
            if (val.matches("red")) {
                output += "" + val + ",";
            }
        }
        for (String val : field) {
            if (val.matches("red")) {
                output += "" + val + ",";
            }
        }
        for (String val : destRed) {
            if (val.matches("red")) {
                output += "" + val + ",";
            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }

    public String getBlue() {
        String output = new String();

        for (String val : startBlue) {
            if (val.matches("blue")) {
                output += "" + val + ",";
            }
        }
        for (String val : field) {
            if (val.matches("blue")) {
                output += "" + val + ",";
            }
        }
        for (String val : destBlue) {
            if (val.matches("blue")) {
                output += "" + val + ",";
            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }

    public String getGreen() {
        String output = new String();

        for (String val : startGreen) {
            if (val.matches("green")) {
                output += "" + val + ",";
            }
        }
        for (String val : field) {
            if (val.matches("green")) {
                output += "" + val + ",";
            }
        }
        for (String val : destGreen) {
            if (val.matches("green")) {
                output += "" + val + ",";
            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }

    public String getYellow() {
        String output = new String();

        for (String val : startYellow) {
            if (val.matches("yellow")) {
                output += "" + val + ",";
            }
        }
        for (String val : field) {
            if (val.matches("yellow")) {
                output += "" + val + ",";
            }
        }
        for (String val : destYellow) {
            if (val.matches("yellow")) {
                output += "" + val + ",";
            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }
}
