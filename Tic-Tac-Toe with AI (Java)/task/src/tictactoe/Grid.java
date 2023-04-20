package tictactoe;


enum Symbol {
    EMPTY, X, O
}
public class Grid {

    private final int SIZE = 3;
    private Symbol[][] symbolArray; // [y][x]

    public int getSIZE() {
        return SIZE;
    }

    public Symbol[][] getSymbolArray() {
        return symbolArray;
    }

    public Symbol checkNextMove() {
        int xCount = 0;
        int oCount = 0;
        for (Symbol[] row: symbolArray) {
            for (Symbol cell: row) {
                if (cell == Symbol.X) {
                    xCount++;
                } else if (cell == Symbol.O) {
                    oCount++;
                }
            }
        }
        if (oCount == xCount) {
            return Symbol.X;
        } else if (oCount < xCount) {
            return Symbol.O;
        } else {
            return Symbol.X;
        }
    }

    public void validateMove(String inputLine, Symbol symbolToPlace) {
        if ("EMPTY".equals(symbolToPlace.name())) {
            throw new IllegalArgumentException("Cannot place an empty cell");
        }
        if (!inputLine.matches("\\d+\\s+\\d+")) {
            throw new NumberFormatException("You should enter numbers!");
        }
        String[] stringCoordinates = inputLine.split("\\s+");
        int[] coordinates = new int[2];
        for (int i = 0; i < coordinates.length; i++) {
            int position = Integer.parseInt(stringCoordinates[i]) - 1; // -1 to convert to array index
            boolean isInBounds = 0 <= position && position < getSIZE();
            if (!isInBounds) {
                throw new IllegalArgumentException(String.format("Coordinates should be from 1 to %d!", getSIZE()));
            }
            coordinates[i] = position;
        }
        Symbol cell = symbolArray[coordinates[0]][coordinates[1]];
        if (!"EMPTY".equals(cell.name())) {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }
        symbolArray[coordinates[0]][coordinates[1]] = symbolToPlace;
    }

    public void printGrid() {
        // print an N x N grid
        StringBuilder border = new StringBuilder();
        int width = getSIZE() * 2 + 3;
        border.append("-".repeat(Math.max(0, width)));
        System.out.println(border);
        for (Symbol[] row: getSymbolArray()) {
            System.out.print("| ");
            for (Symbol cell: row) {
                if ("EMPTY".equals(cell.name())) {
                    System.out.print("  ");
                } else {
                    System.out.print(cell.name()+" ");
                }
            }
            System.out.println("|");
        }
        System.out.println(border);
    }


    public Grid(String symbols) {
        int totalCells = (int) Math.pow(getSIZE(), 2);
        // number of input cells must be equal to the total number of cells, and only contain "_", "X" and "O"
        if (!symbols.matches("[_XO]{"+totalCells+"}\\b")) {
            throw new IllegalArgumentException(
                    String.format("input cells do not correspond to a %dx%d board",getSIZE(), getSIZE()));
        }
        Symbol[][] newSymbols = new Symbol[getSIZE()][getSIZE()];
        for (int i = 0; i < Math.pow(getSIZE(), 2); i+=getSIZE()) { // per cell
            String row = symbols.substring(i,i+getSIZE());
            String[] rowSymbols = row.split("");
            int j = 0;
            while (j < rowSymbols.length) {
                newSymbols[i/getSIZE()][j] = switch (rowSymbols[j]) { // i increases by 3, divide to get actual index
                    case "_" -> Symbol.EMPTY;
                    case "X" -> Symbol.X;
                    case "O" -> Symbol.O;
                    default -> throw new IllegalArgumentException("valid cells are \"_\", \"X\" and \"O\"");
                };
                j++;
            }
        }
        this.symbolArray = newSymbols;
    }
}
