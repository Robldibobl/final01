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
        Game game = null;
        boolean b = true;

        try {
            game = new Game(args[0]);
        } catch (InputException | RuleException e) {
            if (e.getMessage().equals("Error, invalid input in the text file! The program will now exit!")) {
                b = false; //klappt nicht?
            }
            Terminal.printLine(e.getMessage());
        }

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
                        case "search":
                            Terminal.printLine(game.search(param));
                            break;

                        case "route":
                            Terminal.printLine(game.route(param));
                            break;

                        case "insert":
                            Terminal.printLine(game.insert(param));
                            break;

                        case "info":
                            Terminal.printLine(game.info(param));
                            break;

                        case "nodes":
                            Terminal.printLine(game.nodes(param));
                            break;

                        case "vertices":
                            Terminal.printLine(game.vertices(param));
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