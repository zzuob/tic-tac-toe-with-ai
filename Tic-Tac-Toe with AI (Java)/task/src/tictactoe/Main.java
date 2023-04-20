package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Grid grid;
        Symbol nextPlayer;
        while(true) {
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
        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter the coordinates: ");
                if (scan.hasNextLine()) {
                    String coordinates = scan.nextLine();
                    grid.validateMove(coordinates, nextPlayer);
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        grid.printGrid();
        GameState state = State.checkState(grid);
        System.out.println(state.getMessage());
    }
}
