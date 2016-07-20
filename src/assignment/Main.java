package assignment;

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
        Game game;
        boolean run = true;
        boolean active = false;

        while (run == true) {
            try {
                String input = Terminal.readLine();
                String[] optionals = input.split(" ");

                if (optionals[0].equals("start")) {
                    /*
                    Rest an start weitergeben
                     */

                    active = true;
                    Terminal.printLine("OK");
                    game = new Game();

                    while (active == true) {
                        String[] inputArr = input.split(" ");
                        String[] param = new String[0];

                        if (inputArr.length > 2) {
                            throw new InputException("Error, wrong input format!");
                        }

                        if (inputArr.length == 2) {
                            if (inputArr[1].charAt(inputArr[1].length() - 1) == ';') {
                                throw new InputException("Error, wrong input format!");
                            }
                            param = inputArr[1].split(";");
                        }

                        switch (inputArr[0]) {
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
                                active = false;
                                break;

                            case "quit":
                                active = false;
                                run = false;
                                break;

                            default:
                                throw new InputException("Error, unknown command!");
                        }
                    }
                } else if (input.equals("start")) {
                    run = false;
                } else {
                    throw new InputException("Error, unknown command!");
                }
            } catch (InputException | RuleException | IOException e) {
                Terminal.printLine(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                Terminal.printError("Array Index out of Bounds Exception!");
            } catch (NullPointerException e) {
                Terminal.printError("Nullpointer Exception");
            }
        }
    }
}