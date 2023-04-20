package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Grid grid;
        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter the cells: ");
                if (scan.hasNextLine()) {
                    String symbols = scan.nextLine();
                    grid = new Grid(symbols);
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error: %s\n", e.getMessage());
            }
        }
        grid.printGrid();
    }
}
