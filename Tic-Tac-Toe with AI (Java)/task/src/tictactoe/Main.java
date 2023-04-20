package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Grid grid = null;
        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Enter the cells: ");
            String symbols = scan.nextLine();
            grid = new Grid(symbols);
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        }
        assert grid != null;
        grid.printGrid();
    }
}
