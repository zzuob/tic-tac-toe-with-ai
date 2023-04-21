package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean exit = false;
        String playerX = "easy", playerO = "easy";
        while(true){
            boolean isValidCommand = true;
            Scanner scan = new Scanner(System.in);
            System.out.print("Input command: ");
            String command;
            if (scan.hasNextLine()) {
                command = scan.nextLine();
                if ("exit".equals(command)) {
                    exit = true;
                    break;
                }
                String[] commands = command.split(" ");
                if ("start".equals(commands[0]) && commands.length == 3) {
                    // check parameters for who will play
                    String valid = "easy|user";
                    if (commands[1].matches(valid) && commands[2].matches(valid)) {
                        playerX = commands[1];
                        playerO = commands[2];
                        break;
                    } else {
                        isValidCommand = false;
                    }
                } else {
                    isValidCommand = false;
                }
            }
            if (!isValidCommand) {
                System.out.println("Bad parameters!");
            }
        }
        if (!exit) {
            // create a blank board
            Grid grid = new Grid("_________");
            State state = new State(grid);
            state.updateStatus(grid);
            // repeat until a player wins or no more moves can be made
            while ("UNFINISHED".equals(state.getStatus().name())) {
                grid.printGrid();
                Symbol playerSymbol = grid.checkNextMove();
                String currentPlayer;
                // select the current player
                if ("X".equals(playerSymbol.name())) {
                    currentPlayer = playerX;
                } else {
                    currentPlayer = playerO;
                }
                // the current user or computer player takes their turn
                switch (currentPlayer) {
                    case "user" -> Turn.takeUserTurn(grid, playerSymbol);
                    case "easy" -> Turn.takeComputerTurn(grid, playerSymbol, Turn.Difficulty.EASY);
                }
                state.updateStatus(grid); // update the status of the game i.e. UNFINISHED, WIN etc.
            }
            grid.printGrid();
            System.out.println(state.getStatus().getMessage());
        }
    }
}
