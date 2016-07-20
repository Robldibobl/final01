package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Field {
    private String[] startRed;
    private String[] startBlue;
    private String[] startGreen;
    private String[] startYellow;
    private String[] field;
    private String[] destRed;
    private String[] destBlue;
    private String[] destGreen;
    private String[] destYellow;

    public Field() {
        startRed = new String[4];
        startBlue = new String[4];
        startGreen = new String[4];
        startYellow = new String[4];
        field = new String[4];
        destRed = new String[4];
        destBlue = new String[4];
        destGreen = new String[4];
        destYellow = new String[4];
    }

    public void setStart() {
        for (int i = 0; i < 4; i++) {
            startRed[i] = "red";
        }
        for (int i = 0; i < 4; i++) {
            startBlue[i] = "blue";
        }
        for (int i = 0; i < 4; i++) {
            startGreen[i] = "green";
        }
        for (int i = 0; i < 4; i++) {
            startYellow[i] = "yellow";
        }
    }

    public String getRed() {
        String output = new String();
        char[] temp;

        for (String val : startRed) {
            if (val.equals("red")) {
                output += "" + "SR" + ",";
            }
        }
        for (int i = 0; i < 40; i++) {
            if (field[i].equals("red")) {
                output += "" + field[i].indexOf("red") + ",";
            }
        }
        for (int i = 0; i < 4; i++) {
            if (destRed[i].equals("red")) {
                temp = Character.toChars(65 + i);

                output += "" + temp + "R" + ",";

            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }

    public String getBlue() {
        String output = new String();
        char[] temp;

        for (String val : startBlue) {
            if (val.equals("blue")) {
                output += "" + "SB" + ",";
            }
        }
        for (int i = 0; i < 40; i++) {
            if (field[i].equals("blue")) {
                output += "" + field[i].indexOf("blue") + ",";
            }
        }
        for (int i = 0; i < 4; i++) {
            if (destBlue[i].equals("blue")) {
                temp = Character.toChars(65 + i);

                output += "" + temp + "B" + ",";

            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }

    public String getGreen() {
        String output = new String();
        char[] temp;

        for (String val : startGreen) {
            if (val.equals("green")) {
                output += "" + "SG" + ",";
            }
        }
        for (int i = 0; i < 40; i++) {
            if (field[i].equals("green")) {
                output += "" + field[i].indexOf("green") + ",";
            }
        }
        for (int i = 0; i < 4; i++) {
            if (destGreen[i].equals("green")) {
                temp = Character.toChars(65 + i);

                output += "" + temp + "G" + ",";

            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }

    public String getYellow() {
        String output = new String();
        char[] temp;

        for (String val : startYellow) {
            if (val.equals("yellow")) {
                output += "" + "SY" + ",";
            }
        }
        for (int i = 0; i < 40; i++) {
            if (field[i].equals("yellow")) {
                output += "" + field[i].indexOf("yellow") + ",";
            }
        }
        for (int i = 0; i < 4; i++) {
            if (destYellow[i].equals("yellow")) {
                temp = Character.toChars(65 + i);

                output += "" + temp + "Y" + ",";

            }
        }

        output = output.substring(0, output.lastIndexOf(",")) + "\n";
        return output;
    }
}