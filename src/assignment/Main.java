package assignment;

import edu.kit.informatik.Terminal;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Main {
    private static boolean run = true;
    private static boolean active = false;

    /**
     * Main method; splits input into commands and parameters.
     *
     * @param args Commandline parameters
     */
    public static void main(String[] args) {
        Game game;

        while (run) {
            try {
                String input = Terminal.readLine();
                if (input.charAt(input.length() - 1) == ' ') {
                    throw new InputException("Error, wrong input format!");
                }
                String[] optionals = input.split(" ");

                if (optionals[0].equals("start")) {
                    game = new Game();

                    for (String val : optionals) {
                        Check.checkString(val);
                    }

                    Terminal.printLine(game.start(optionals));
                    active = true;

                    while (active) {
                        try {
                            String command = Terminal.readLine();
                            String[] inputArr = command.split(" ");
                            String[] param = new String[inputArr.length - 1];
                            System.arraycopy(inputArr, 1, param, 0, inputArr.length - 1);

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
                        } catch (InputException | RuleException e) {
                            Terminal.printLine(e.getMessage());
                        }
                    }
                } else if (input.equals("quit")) {
                    run = false;
                } else {
                    throw new InputException("Error, unknown command!");
                }
            } catch (InputException e) {
                Terminal.printLine(e.getMessage());
            }
        }
    }

    /**
     * Setter for the boolean active.
     *
     * @param active Boolean that determines whether or not a game is active
     */
    public static void setActive(boolean active) {
        Main.active = active;
    }
}