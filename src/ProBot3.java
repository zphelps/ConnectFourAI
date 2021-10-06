import java.util.ArrayList;
import java.util.Collections;

public class ProBot3 {
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
        else if(depth == 8) {
            int o = board.numOneAway("O");
            int x = board.numOneAway("X");
            if(o > x) {
                return o; //-10 o * depth
            }
            else if(x > o) {
                return x; //10 x* depth
            }
            return (int) (Math.random()*2) == 0 ? -1 : 1;
        }

        ArrayList<Integer> emptyLocations = board.getEmptyLocations();
        ArrayList<Integer> filteredLocations = new ArrayList<>();

        for(int l = emptyLocations.size() / 2; l >= 0; l--) {
            filteredLocations.add(emptyLocations.get(l));
        }
        for(int l = emptyLocations.size() / 2+1; l < emptyLocations.size(); l++) {
            filteredLocations.add(emptyLocations.get(l));
        }

        emptyLocations = filteredLocations;

        for(int l = 0; l < emptyLocations.size(); l++) {

            board.place(emptyLocations.get(l), turn);

            if(turn.equals("O")) {
                int score = minMax(board, "X", depth+1, alpha, beta);

                //Standard code
                if(score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = emptyLocations.get(l);
                }

            }
            else {
                int score = minMax(board, "O", depth+1, alpha, beta);

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
        minMax(board, turn, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return myMove;
    }

    public void setMove(int move) {
        myMove = move;
    }
}
