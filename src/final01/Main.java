package final01;

import edu.kit.informatik.Terminal;

import java.io.IOException;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Main {

    /**
     * Main method; splits input into commands and parameters.
     *
     * @param args Commandline parameters
     */
    public static void main(String[] args) {
        Game game = new Game();
        boolean b = true;

        while (b == true) {
            String input = Terminal.readLine();
            if (input.equals("quit")) {
                b = false;
            } else {

                try {
                    String[] inputArr = input.split(" ");
                    if (inputArr.length > 2) {
                        throw new InputException("Error, wrong input format!");
                    }
                    String[] param = new String[0];
                    if (inputArr.length == 2) {
                        if (inputArr[1].charAt(inputArr[1].length() - 1) == ';') {
                            throw new InputException("Error, wrong input format!");
                        }
                        param = inputArr[1].split(";");
                    }

                    switch (inputArr[0]) {
                        case "start":
                            Terminal.printLine(game.start(param));
                            break;

                        case "roll":
                            Terminal.printLine(game.roll(param));
                            break;

                        case "rollx":
                            Terminal.printLine(game.rollX(param));
                            break;

                        case "move":
                            Terminal.printLine(game.move(param));
                            break;

                        case "print":
                            Terminal.printLine(game.print(param));
                            break;

                        case "abort":
                            game.abort(param);
                            break;

                        default:
                            throw new InputException("Error, unknown command!");
                    }
                } catch (InputException | RuleException | IOException e) {
                    Terminal.printLine(e.getMessage());
                }
            }
        }
    }
}