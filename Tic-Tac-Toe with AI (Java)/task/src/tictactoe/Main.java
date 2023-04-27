package tictactoe;

import java.util.Scanner;

public class Main {

    private static String getCommandFromInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input command: ");
        if (scan.hasNextLine()) {
            return scan.nextLine();
        } else {
            return "";
        }
    }

    private static void runGame(String playerX, String playerO) {
        // run a new game of tic-tac-toe
        Grid grid = new Grid("_________");
        State state = new State(grid);
        state.updateStatus(grid);
        // repeat until a player wins or no more moves can be made
        while ("UNFINISHED".equals(state.getStatus().name())) {
            grid.printGrid();
            Symbol playerSymbol = grid.checkNextMove();
            String currentPlayerMode;
            // select the current player
            if (playerSymbol == Symbol.X) {
                currentPlayerMode = playerX;
            } else {
                currentPlayerMode = playerO;
            }
            // the current user or computer player takes their turn
            switch (currentPlayerMode) {
                case "user" -> Turn.takeUserTurn(grid, playerSymbol);
                case "easy" -> Turn.takeComputerTurn(grid, playerSymbol, Turn.Difficulty.EASY);
                case "medium" -> Turn.takeComputerTurn(grid, playerSymbol, Turn.Difficulty.MEDIUM);
            }
            state.updateStatus(grid); // update the status of the game i.e. UNFINISHED, WIN etc.
        }
        grid.printGrid();
        System.out.println(state.getStatus().getMessage());
    }

    public static void main(String[] args) {

        String playerModes = "medium|easy|user";
        String command = getCommandFromInput();
        while(!"exit".equals(command)) {
            boolean isValidCommand = false;
            String[] commands = command.split(" ");
            if ("start".equals(commands[0]) && commands.length == 3) {
                // check parameters for who will play each side (i.e. user or AI level)
                if (commands[1].matches(playerModes) && commands[2].matches(playerModes)) {
                    String playerXMode = commands[1];
                    String playerOMode = commands[2];
                    isValidCommand = true;
                    runGame(playerXMode, playerOMode);
                }
            }
            if (!isValidCommand) {
                System.out.println("Bad parameters!");
                //System.out.println("Enter a valid command: \"start player_mode player_mode\" or \"exit\"");
                //System.out.printf("where player_mode = %s\n", playerModes);
            }
            command = getCommandFromInput();
        }

    }
}
