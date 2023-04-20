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

    private static GameState checkWinner(Symbol winSymbol) {
        if ("X".equals(winSymbol.name())) {
            return GameState.X_WINS;
        } else {
            return GameState.O_WINS;
        }
    }
    public static GameState checkState(Grid grid) {
        Symbol[][] board = grid.getSymbolArray();
        // horizontal checks
        for (Symbol[] row : board) {
            int count = 0;
            Symbol winSymbol = row[0];
            if ("EMPTY".equals(winSymbol.name())) {
                continue;
            }
            for (Symbol cell : row) {
                if (cell == winSymbol) {
                    count++;
                }
            }
            if (count == grid.getSIZE()) {
                return checkWinner(winSymbol);
            }
        }
        // vertical checks
        for (int i = 0; i < grid.getSIZE(); i++) {
            int count = 0;
            Symbol winSymbol = board[0][i];
            if ("EMPTY".equals(winSymbol.name())) {
                continue;
            }
            for (int j = 0; j < grid.getSIZE(); j++) {
                count += board[j][i] == winSymbol ? 1 : 0;
            }
            if (count == grid.getSIZE()) {
                return checkWinner(winSymbol);
            }
        }
        // diagonal down (from 1,1)
        Symbol winSymbol = board[0][0];
        for (int i = 1; i < grid.getSIZE(); i++) {
            if (winSymbol != board[i][i]) {
                break;
            } else if (i == grid.getSIZE() - 1) {
                return checkWinner(winSymbol);
            }
            winSymbol = board[i][i];
        }
        // other diagonal
        int y = grid.getSIZE() - 1;
        Symbol previous = board[y][0];
        System.out.printf("board[2][0] = %s\n", previous.name());
        for (int i = 1; i < grid.getSIZE(); i++) {
            System.out.printf("board[%d][%d] = %s\n", y-i, i, board[y-i][i].name());
            if (previous != board[y-i][i]) {
                break;
            } else if (i == grid.getSIZE() - 1) {
                return checkWinner(previous);
            }
            previous = board[y-i][i];
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
