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

    public void printGrid() {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < Math.pow(getSIZE(), 2); i++) {
            border.append("-");
        }
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
