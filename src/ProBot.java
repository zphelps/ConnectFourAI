import java.util.ArrayList;
import java.util.Collections;

public class ProBot {
    private int myMove;

    public int minMax(Board board, String turn, int depth, int alpha, int beta) {
        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = Integer.MIN_VALUE;
        int bestMoveSoFar = 0;

        if(board.checkWinner("X")) {
            return 10;
        }
        else if(board.checkWinner("O")) {
            return -10;
        }
        else if(board.getEmptyLocations().size() == 0) {
            return 0;
        }
        else if(depth == 8) {
            int x = board.numOneAway("X");
            int o = board.numOneAway("O");
            if(x > o) {
                return x; //-10
            }
            else if(o > x) {
                return o; //10
            }
            return (int) (Math.random()*2) == 0 ? -3 : 3; //-5, 5
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

//            if(!board.getBoard()[board.getNumRows()-1][board.getNumColumns()/2].equals("O") && !board.getBoard()[board.getNumRows()-1][board.getNumColumns()/2].equals("X")) {
////                bestMoveSoFar = board.getNumColumns()/2+1;
////                break;
////            }
//            if(!board.getBoard()[5][3].equals("O") && !board.getBoard()[5][3].equals("X")) {
//                bestMoveSoFar = 2;
//                break;
//            }
            board.place(emptyLocations.get(l), turn);

            if(turn.equals("X")) {
                int score = minMax(board, "O", depth+1, alpha, beta);

                //Standard code
                if(score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = emptyLocations.get(l);
                }

            }
            else {
                int score = minMax(board, "X", depth+1, alpha, beta);

//                minSoFar = Math.min(minSoFar, score);
//                beta = Math.min(beta, score);
//                if(beta <= alpha) {
//                    board.remove(emptyLocations.get(l));
//                    break;
//                }

                //Standard Code
                if(score < minSoFar) {
                    minSoFar = score;
                }

            }

            board.remove(emptyLocations.get(l));

        }

        setMove(bestMoveSoFar);

        if(turn.equals("X")) {
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
