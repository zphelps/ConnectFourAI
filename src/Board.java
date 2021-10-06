import java.util.ArrayList;

public class Board {
    private String[][] board;
    private final int ROWS;
    private final int COLUMNS;

    public Board(int rows, int columns) {
        ROWS = rows;
        COLUMNS = columns;
        board = new String[ROWS][COLUMNS];
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                board[row][col] = "-";
            }
        }
    }

    public void place(int col, String mark) {
        for(int row = ROWS-1; row >= 0; row--) {
            if(!board[row][col-1].equals("X") && !board[row][col-1].equals("O")) {
                board[row][col-1] = mark;
                break;
            }

        }
    }

    public void remove(int col) {
        for(int row = 0; row < ROWS; row++) {
            if(board[row][col-1].equals("X") || board[row][col-1].equals("O")) {
                board[row][col-1] = "-";
                break;
            }
        }
    }

    public boolean checkWinner(String mark) {
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                if(board[row][col].equals(mark)) {
                    if (col + 3 < COLUMNS && board[row][col+1].equals(mark) && board[row][col+2].equals(mark) && board[row][col+3].equals(mark))
                        return true;
                    if (row + 3 < ROWS) {
                        if (board[row+1][col].equals(mark) && board[row+2][col].equals(mark) && board[row+3][col].equals(mark))
                            return true;
                        if (col + 3 < COLUMNS && board[row+1][col+1].equals(mark) && board[row+2][col+2].equals(mark) && board[row+3][col+3].equals(mark))
                            return true;
                        if (col - 3 >= 0 && board[row+1][col-1].equals(mark) && board[row+2][col-2].equals(mark) && board[row+3][col-3].equals(mark))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public int numOneAway(String mark) {
        int count = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                if(board[row][col].equals(mark)) {
                    if (col + 2 < COLUMNS && board[row][col+1].equals(mark) && board[row][col+2].equals(mark))
                        count++;
                    if (row + 2 < ROWS) {
                        if (board[row+1][col].equals(mark) && board[row+2][col].equals(mark))
                            count++;
                        if (col + 2 < COLUMNS && board[row+1][col+1].equals(mark) && board[row+2][col+2].equals(mark))
                            count++;
                        if (col - 2 >= 0 && board[row+1][col-1].equals(mark) && board[row+2][col-2].equals(mark))
                            count++;
                    }
                }
            }
        }
        return count;
    }

    public int numTwoAway(String mark) {
        int count = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                if(board[row][col].equals(mark)) {
                    if (col + 1 < COLUMNS && board[row][col+1].equals(mark))
                        count++;
                    if (row + 1 < ROWS) {
                        if (board[row+1][col].equals(mark))
                            count++;
                        if (col + 1 < COLUMNS && board[row+1][col+1].equals(mark))
                            count++;
                        if (col - 1 >= 0 && board[row+1][col-1].equals(mark))
                            count++;
                    }
                }
            }
        }
        return count;
    }

    public ArrayList<Integer> getEmptyLocations() {
        ArrayList<Integer> openSpots = new ArrayList<>();
        for(int c = 0; c < COLUMNS; c++) {
            if(!board[0][c].equals("X") && !board[0][c].equals("O")) {
                openSpots.add(c+1);
            }
        }
        return openSpots;
    }

    public int getNumRows() {
        return ROWS;
    }

    public int getNumColumns() {
        return COLUMNS;
    }

    public String[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder boardOutput = new StringBuilder();

        for(int col = 0; col < COLUMNS; col++) {
            boardOutput.append(col + 1).append(" ");
        }
        boardOutput.append("\n");
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                boardOutput.append(board[row][col]).append(" ");
            }
            boardOutput.append("\n");
        }
        return boardOutput.toString();
    }

}
