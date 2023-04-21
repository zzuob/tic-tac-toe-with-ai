package tictactoe;

public class Main {
    public static void main(String[] args) {

        // create a blank board
        Grid grid = new Grid("_________");
        State state = new State(grid);
        state.updateStatus(grid);
        while("UNFINISHED".equals(state.getStatus().name())) {
            grid.printGrid();
            Symbol player = grid.checkNextMove();
            if ("X".equals(player.name())) {
                Turn.takeUserTurn(grid, Symbol.X);
            } else {
                Turn.takeUserTurn(grid, Symbol.O);
            }
            state.updateStatus(grid);
            System.out.println(state.getStatus().getMessage());
        }
        grid.printGrid();
    }
}
