package tictactoe;

public class Main {
    public static void main(String[] args) {

        // create a blank board
        Grid grid = new Grid("_________");
        State state = new State(grid);
        grid.printGrid();
        Turn.takeUserTurn(grid, Symbol.X);
        grid.printGrid();
        state.updateStatus(grid);
        System.out.println(state.getStatus().getMessage());
    }
}
