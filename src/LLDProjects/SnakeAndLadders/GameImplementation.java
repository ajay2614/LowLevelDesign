package LLDProjects.SnakeAndLadders;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class BoardSAL {
    int row;
    int col;
    HashMap<Integer, Integer> snakeMap;
    HashMap<Integer, Integer> ladderMap;
    public BoardSAL(int row, int col) {
        this.row = row;
        this.col = col;
        snakeMap = new HashMap<>();
        ladderMap = new HashMap<>();
    }

    public void assignSnakeAndLadders(int totalSnake, int totalLadder) {
        int totalPositions = row * col - 1;
        while (totalSnake > 0) {
            int tail = ThreadLocalRandom.current().nextInt(1, totalPositions);
            int head = ThreadLocalRandom.current().nextInt(tail, totalPositions);

            if(!checkIfAlreadyPresent(tail, head)) {
                totalSnake--;
                snakeMap.put(head, tail);
            }
        }
        while (totalLadder > 0) {
            int bottom = ThreadLocalRandom.current().nextInt(1, totalPositions);
            int top = ThreadLocalRandom.current().nextInt(bottom, totalPositions);

            if(!checkIfAlreadyPresent(bottom, top)) {
                totalLadder--;
                ladderMap.put(bottom, top);
            }
        }
    }

    public boolean checkIfAlreadyPresent(int bottom, int top) {
        return bottom == top || snakeMap.containsKey(bottom) || snakeMap.containsKey(top)
                || snakeMap.containsValue(bottom) || snakeMap.containsValue(top)
                || ladderMap.containsKey(bottom) || ladderMap.containsKey(top)
                || ladderMap.containsValue(bottom) || ladderMap.containsValue(top);
    }


}

class Dice {
    int totalDice;

    public Dice(int totalDice) {
        this.totalDice = totalDice;
    }
    public int throwDice() {
        int val = 0;
        for (int i=0 ; i < totalDice ; i++) {
            val += ThreadLocalRandom.current().nextInt(1, 7);
        }
        return val;
    }
}

class Player {
    String name;
    int pos;
    public Player(String name, int pos) {
        this.name = name;
        this.pos = pos;
    }

}
public class GameImplementation {

    public Deque<Player> players;
    Dice dice;
    BoardSAL boardSAL;
    GameImplementation(List<String> playerList, int totalDice, int totalRow, int totalColumn, int totalSnake, int totalLadder) {
        this.players = new LinkedList<>();
        for(String playerName : playerList) {
            this.players.add(new Player(playerName, 0));
        }

        this.dice = new Dice(totalDice);
        boardSAL = new BoardSAL(totalRow, totalColumn);
        boardSAL.assignSnakeAndLadders(totalSnake, totalLadder);
    }

    public void startGame() {

        while (true) {
            Player player = players.removeFirst();
            int throwDice = dice.throwDice();
            int currentPos = player.pos + throwDice;

            if(boardSAL.snakeMap.containsKey(currentPos)) {
                currentPos = boardSAL.snakeMap.get(currentPos);
            }
            if(boardSAL.ladderMap.containsKey(currentPos)) {
                currentPos = boardSAL.ladderMap.get(currentPos);
            }
            if(currentPos >= (boardSAL.row * boardSAL.col - 1)) {
                System.out.println("Player -> " + player.name + " is the winner");
                return;
            }

            System.out.println("Player -> " + player.name + " moved from position -> " + player.pos + " to " + currentPos);
            player.pos = currentPos;
            players.addLast(player);
        }
    }
}
