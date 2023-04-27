package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Turn {

    enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private static int[] checkTwoInARow(Grid grid, Symbol player) {
        // check for two in a row
        Symbol[][] board = grid.getSymbolArray();
        int[][][] configs = State.getConfigs(grid);
        for (int[][] config: configs) {
            int[] sym1Coordinates = config[0];
            int[] sym2Coordinates = config[1];
            int[] sym3Coordinates = config[2];
            boolean isSym1 = player == board[sym1Coordinates[0]][sym1Coordinates[1]];
            boolean isSym2 = player == board[sym2Coordinates[0]][sym2Coordinates[1]];
            boolean isSym3 = player == board[sym3Coordinates[0]][sym3Coordinates[1]];
            if (isSym1 && isSym2) {
                return sym3Coordinates;
            } else if (isSym1 && isSym3) {
                return sym2Coordinates;
            } else if (isSym2 && isSym3) {
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
            } catch (Exception e) {
                // do nothing - computer tries to place again
            }
        }
    }
    public static void takeComputerTurn(Grid grid, Symbol player, Difficulty level) {
        System.out.printf("Making move level \"%s\"\n", level.name().toLowerCase());
        if ("EASY".equals(level.name())) {
            takeRandomMove(grid, player);
        } else if ("MEDIUM".equals(level.name())) {
            takeRandomMove(grid, player);
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
