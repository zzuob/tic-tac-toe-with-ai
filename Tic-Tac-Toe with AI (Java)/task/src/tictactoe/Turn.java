package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Turn {

    enum Difficulty {
        EASY, MEDIUM, HARD
    }


    private static int[] checkTwoInARow(Grid grid, Symbol player) {
        // check the grid for a two in a row in the specified player, return the missing move
        Symbol[][] board = grid.getSymbolArray();
        int[][][] configs = State.getConfigs(grid);
        for (int[][] config: configs) {
            int[] sym1Coordinates = config[0];
            int[] sym2Coordinates = config[1];
            int[] sym3Coordinates = config[2];
            Symbol sym1 = board[sym1Coordinates[0]][sym1Coordinates[1]];
            Symbol sym2 = board[sym2Coordinates[0]][sym2Coordinates[1]];
            Symbol sym3 = board[sym3Coordinates[0]][sym3Coordinates[1]];
            boolean isSym1 = player == sym1;
            boolean isSym2 = player == sym2;
            boolean isSym3 = player == sym3;
            if (isSym1 && isSym2 && sym3 == Symbol.EMPTY) {
                return sym3Coordinates;
            }
            if (isSym1 && sym2 == Symbol.EMPTY && isSym3) {
                return sym2Coordinates;
            }
            if (sym1 == Symbol.EMPTY && isSym2 && isSym3) {
                return sym1Coordinates;
            }
        }
        return null;
    }

    private static void takeRandomMove(Grid grid, Symbol player) {
        // attempt to place a symbol randomly until a valid placement is found
        while(true) {
            try {
                Random random = new Random();
                int y = random.nextInt(grid.getSIZE() + 1) + 1;
                int x = random.nextInt(grid.getSIZE() + 1) + 1;
                String coordinates = y + " " + x;
                grid.validateMove(coordinates, player);
                break;
            } catch (Exception ignored) { // try to place again
            }
        }
    }

    private static void takeMove(Grid grid, Symbol player, int[] move) throws IllegalArgumentException {
        // int[] move = { (0 to 2), (0 to 2) }
        int y = move[0] + 1;
        int x = move[1] + 1;
        String coordinates = y + " " + x;
        grid.validateMove(coordinates, player);
    }

    private static Symbol getOtherPlayer(Symbol player) {
        if (player == Symbol.EMPTY) {
            throw new IllegalArgumentException("EMPTY is not a player");
        } else if (player == Symbol.X) {
            return Symbol.O;
        } else {
            return Symbol.X;
        }
    }
    public static void takeComputerTurn(Grid grid, Symbol player, Difficulty level) {
        System.out.printf("Making move level \"%s\"\n", level.name().toLowerCase());
        if ("EASY".equals(level.name())) {
            takeRandomMove(grid, player);
        } else if ("MEDIUM".equals(level.name())) {
            int[] nextMove = checkTwoInARow(grid, player); // complete any 2-in-a-row the AI has
            if (nextMove == null) {
                nextMove = checkTwoInARow(grid, getOtherPlayer(player)); // move to block opponent from winning
            }
            if (nextMove == null) {
                takeRandomMove(grid, player);
            } else {
                try {
                    takeMove(grid, player, nextMove);
                } catch (Exception e) {
                    System.out.printf("Error: %s\nMaking a random move instead\n", e.getMessage());
                    takeRandomMove(grid, player);
                }
            }
        }
    }

    public static void takeUserTurn(Grid grid, Symbol player) {
        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter the coordinates: ");
                if (scan.hasNextLine()) {
                    String coordinates = scan.nextLine();
                    grid.validateMove(coordinates, player);
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @SuppressWarnings("unused")
    public Grid prepareGrid() {
        Grid grid;
        Symbol nextPlayer;

        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter the cells: ");
                if (scan.hasNextLine()) {
                    String symbols = scan.nextLine();
                    grid = new Grid(symbols);
                    grid.printGrid();
                    nextPlayer = grid.checkNextMove();
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error: %s\n", e.getMessage());
            }
        }
        return grid;
    }
}
