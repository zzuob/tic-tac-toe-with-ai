package tictactoe;


enum GameState {
    UNFINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins");

    private final String message;

    GameState(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
public class State {

    private GameState status;
    private final int[][][] winConfigs;


    public void updateStatus(Grid grid) {
        this.status = checkState(grid);
    }

    public GameState getStatus() {
        return status;
    }

    public State(Grid grid) {
        this.winConfigs = getConfigs(grid);
    }

    private static GameState checkWinner(Symbol winSymbol) {
        if ("X".equals(winSymbol.name())) {
            return GameState.X_WINS;
        } else {
            return GameState.O_WINS;
        }
    }

    public static int[][][] getConfigs (Grid grid) {
        int winConditions = (grid.getSIZE() * 2) + 2; // all perpendicular and 2 diagonals
        int[][][] winConfigs = new int[winConditions][3][2];
        for (int i = 0; i < grid.getSIZE(); i++) {
            int[][] horizontal = new int[][]{{i,0}, {i,1}, {i,2}};
            int[][] vertical = new int[][]{{0,i}, {1,i}, {2,i}};
            winConfigs[i] = horizontal;
            winConfigs[i+grid.getSIZE()] = vertical;
        }
        // add diagonals
        winConfigs[grid.getSIZE()*2] = new int[][]{{0,0},{1,1},{2,2}};
        winConfigs[grid.getSIZE()*2+1] = new int[][]{{2,0},{1,1},{0,2}};
        return winConfigs;
    }


    public GameState checkState(Grid grid) {
        Symbol[][] board = grid.getSymbolArray();
        for (int[][] config: winConfigs) {
            Symbol sym1 = board[config[0][0]][config[0][1]];
            Symbol sym2 = board[config[1][0]][config[1][1]];
            Symbol sym3 = board[config[2][0]][config[2][1]];
            boolean isThreeInARow = sym1.name().equals(sym2.name()) && sym1.name().equals(sym3.name());
            boolean isPlaced = !"EMPTY".equals(sym1.name());
            if (isThreeInARow && isPlaced) {
                return checkWinner(sym1);
            }

        }
        for (Symbol[] row : board) {
            for (Symbol cell : row) {
                if ("EMPTY".equals(cell.name())) {
                    return GameState.UNFINISHED;
                }
            }
        }
        return GameState.DRAW;
    }
}
