import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int spot;
        String turn;
        boolean gameOver = false;
        Board board = new Board(6, 7);

        ProBot proBot = new ProBot();
        ProBot2 proBot2 = new ProBot2();
        ProBot3 proBot3 = new ProBot3();
        System.out.println(board.toString());
        turn = "O"; //X for testing

        while(gameOver == false)
        {
            if(turn.equals("O")) {
                System.out.print("Enter your move: ");
                spot = input.nextInt();
                board.place(spot, "O");
                turn = "X";
//                int move = proBot3.getMove(board, "O");
//                System.out.println("Custom Heuristic Bot Move: " + move);
////                System.out.println("Move: " + move);
//                board.place(move, "O");
//                turn = "X";
            }
            else if(turn.equals("X")) {
//                System.out.print("Enter your move: ");
//                spot = input.nextInt();
//                board.place(spot, "X");
//                turn = "O";
                System.out.println("Standard Heuristic Bot Move:");
                int move = proBot.getMove(board, "X");
                System.out.println("Move: " + move);
                board.place(move, "X");
                turn = "O";
            }
            if(board.checkWinner("O")) {
//                System.out.println("You beat the Pro Bot. Congrats!");
                System.out.println("Custom Heuristic Wins");
                gameOver = true;
            }
            else if(board.checkWinner("X")) {
//                System.out.println("The Pro Bot wins!");
                System.out.println("Pro Bot wins");
                gameOver = true;
            }
            else if(board.getEmptyLocations().size() == 0) {
                System.out.println("Cat's Game!");
                gameOver = true;
            }
            System.out.println(board.toString());

        }

    }
}
