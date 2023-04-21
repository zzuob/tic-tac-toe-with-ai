package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Turn {

    enum Difficulty {
        EASY, MEDIUM, HARD
    }
    public static void takeComputerTurn(Grid grid, Symbol player, Difficulty level) {
        System.out.printf("Making move level \"%s\"\n", level.name().toLowerCase());
        if ("EASY".equals(level.name())) {
            while(true) {
                try {
                    Random random = new Random();
                    int y = random.nextInt(grid.getSIZE()+1) + 1;
                    int x = random.nextInt(grid.getSIZE()+1) + 1;
                    String coordinates = y + " " + x;
                    grid.validateMove(coordinates, player);
                    break;
                } catch (Exception e) {
                    // do nothing - computer tries to place again
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
    @Deprecated
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
