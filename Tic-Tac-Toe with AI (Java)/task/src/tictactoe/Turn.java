package tictactoe;

import java.util.Scanner;

public class Turn {


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
