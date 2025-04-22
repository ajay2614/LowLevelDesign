package TicTacToeLLD;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameImplementation {

    Deque<Player> players;

    public void initializeGame() {
        players = new LinkedList<>();

        Player player1 = new Player("Player1", new PlayingPieceX());
        Player player2 = new Player("Player2", new PlayingPieceO());

        players.add(player1);
        players.add(player2);

        Board board = new Board(3);
        startGame(players, board);
    }

    public void startGame(Deque<Player> players, Board board) {
        boolean isNotFinished = true;
        boolean winnerFound = false;
        while(isNotFinished) {

            List<Pair<Integer, Integer>>  freeSpaces = board.getFreeSpaces();
            if(freeSpaces.isEmpty()) {
                isNotFinished = false;
                continue;
            }
            Player player = players.removeFirst();

            System.out.println("The board as of now is");
            board.printBoard();

            System.out.println("Enter the row,column");

            Scanner scanner = new Scanner(System.in);

            String inp = scanner.next();

            String[] res = inp.split(",");

            int row = Integer.parseInt(res[0]);
            int col = Integer.parseInt(res[1]);

            boolean addPiece = board.addPiece(row, col, player.playingPiece);

            if(!addPiece) {
                players.addFirst(player);
                System.out.println("Wrong Position Entered, try again");
                continue;
            }

            boolean isWinner = winGame(row, col, player.playingPiece, board.getBoard());
            if(isWinner) {
                winnerFound = true;
                System.out.println(player.getName() + " is the winner");
                break;
            }
            players.addLast(player);
        }
        if(!winnerFound)
            System.out.println("its a draw");
    }

    public boolean winGame(int row, int col, PlayingPiece playingPieceType, PlayingPiece[][] board) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonal = true;
        boolean antiDiagonal = true;

        for(int i=0; i<board.length;i++) {
            if(board[row][i] == null || board[row][i] != playingPieceType) {
                rowMatch = false;
                break;
            }
        }
        for(int i=0; i<board[0].length;i++) {
            if(!colMatch)
                continue;
            if(board[i][col] == null || board[i][col] != playingPieceType)
                colMatch = false;
        }
        for(int i=0,j=0;i<board.length; i++,j++) {
            if(!diagonal)
                continue;
            if(board[i][j] == null || board[i][j] != playingPieceType)
                diagonal = false;
        }
        for(int i=0,j= board.length-1;i<board.length;i++,j--) {
            if (!antiDiagonal)
                continue;
            if (board[i][j] == null || board[i][j] != playingPieceType)
                antiDiagonal = false;
        }

        return rowMatch || colMatch || diagonal || antiDiagonal;
    }

    public static void main(String[] args) {
        GameImplementation gameImplementation = new GameImplementation();
        gameImplementation.initializeGame();
    }

}
