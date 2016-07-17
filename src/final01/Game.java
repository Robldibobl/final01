package final01;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Game {
    private Token token;
    private int currentPlayer;

    public Game() {
        token = new Token();
    }

    /*
    optionale Spielregeln
     */

    /*

     */
    private void turn() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else if (currentPlayer == 2) {
            currentPlayer = 3;
        } else if (currentPlayer == 3) {
            currentPlayer = 4;
        } else if (currentPlayer == 4) {
            currentPlayer = 1;
        }
    }

    public void start(String[] param) {
        Check.checkAmount(param, x);
        Check.checkOptionals();

    }

    public void roll(String[] param) throws InputException {
        Check.checkAmount(param, 1);
    }

    public void rollX(String[] param) throws InputException {
        Check.checkAmount(param, 0);

        return roll(random);
    }

    public void move(String[] param) throws InputException {
        Check.checkAmount(param, 2);
        Check.checkInteger(Integer.parseInt(param[0]));
        Check.checkInteger(Integer.parseInt(param[1]));

        //in Check nur Spielfeld einbezogen, Zielfelder wie lösen?

    }

    public String print(String[] param) throws InputException {
        Check.checkAmount(param, 0);
        String output = new String();

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                output += "" + token.getRed();
            }
            if (i == 1) {
                output += "" + token.getBlue();
            }
            if (i == 2) {
                output += "" + token.getGreen();
            }
            if (i == 3) {
                output += "" + token.getYellow();
            }
        }

        output = output + "\n" + getCurrentPlayer();
        return output;
    }

    public void abort(String[] param) throws InputException {
        Check.checkAmount(param, 0);
    }

    public String getCurrentPlayer() {
        for () {

        }

        return currentPlayer;
    }
}
