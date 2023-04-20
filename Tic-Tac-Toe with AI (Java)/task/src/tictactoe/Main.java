package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Enter the cells: ");
            String symbols = scan.nextLine();
            Grid grid = new Grid(symbols);
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }
}
