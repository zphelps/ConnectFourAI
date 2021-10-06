import java.util.ArrayList;

public class ProBot2 {
    private int myMove;

    public int minMax(Board board, String turn, int depth, int alpha, int beta) {
        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = Integer.MIN_VALUE;
        int bestMoveSoFar = 0;

        if(board.checkWinner("O")) {
            return 10;
        }
        else if(board.checkWinner("X")) {
            return -10;
        }
        else if(board.getEmptyLocations().size() == 0) {
            return 0;
        }
        else if(depth == 0) {

            int o = board.numOneAway("O");
            int x = board.numOneAway("X");
            if(o > x) {
                return o; //-10
            }
            else if(x > o) {
                return x; //10
            }
            return (int) (Math.random()*2) == 0 ? -1 : 1;
        }

        ArrayList<Integer> emptyLocations = board.getEmptyLocations();
        for(int l = 0; l < emptyLocations.size(); l++) {
            board.place(emptyLocations.get(l), turn);

            if(turn.equals("O")) {
                int score = minMax(board, "X", depth-1, alpha, beta);

                //Standard code
                if(score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = emptyLocations.get(l);
                }

            }
            else {
                int score = minMax(board, "O", depth-1, alpha, beta);
                //Standard Code
                if(score < minSoFar) {
                    minSoFar = score;
                }

            }

            board.remove(emptyLocations.get(l));

        }

        setMove(bestMoveSoFar);

        if(turn.equals("O")) {
            return maxSoFar;
        }
        else {
            return minSoFar;
        }

    }

    public int getMove(Board board, String turn) {
        minMax(board, turn, 8, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return myMove;
    }

    public void setMove(int move) {
        myMove = move;
    }
}
