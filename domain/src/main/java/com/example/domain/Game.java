package com.example.domain;

import com.example.domain.utils.Constants;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import sun.rmi.runtime.Log;

public class Game {

    private static final String TAG = "Game";

    private Player player1, player2;
    private Player currentPlayer;
    private Cell[][] cells;

    private Subject<Player> winner = PublishSubject.create();

    public Game(String name1, String name2) {
        cells = new Cell[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        this.player1 = new Player(name1, "X");
        this.player2 = new Player(name2, "O");
        currentPlayer = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    //Logic to check whether the game has ended
    public boolean hasGameEnded() {
        if(hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() ||
                hasThreeSameDiagonalCells()) {
            winner.onNext(currentPlayer);
            winner.onComplete();
            return true;
        }

        if(isBoardFull()) {
            Player player = new Player();
            winner.onNext(player);
            winner.onComplete();
            return true;
        }

        return false;
    }

    //Check whether the user has entered same values in the three consecutive cells horizontally
    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < Constants.BOARD_SIZE; i++)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;
            return false;
        } catch(NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    //Check whether the user has entered same values in three consecutive cells vertically
    public boolean hasThreeSameVerticalCells() {
        try {
            for(int i=0 ; i < Constants.BOARD_SIZE; i++)
                if(areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        } catch(NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    //Check whether the user has entered same values in three consecutive cells diagonally
    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch(NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    //Check whether the board is full and there is no cell left for any user to enter
    public boolean isBoardFull() {
        for(Cell[] row : cells)
            for(Cell cell : row)
                if(cell == null || cell.isEmpty())
                    return false;
        return true;
    }

    //Reset all the values
    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }


    //Comparing whether values entered in different cells are all same or not
    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Cell cell : cells)
            if (cell == null || StringUtility.isNullOrEmpty(cell.getPlayer().getValue()))
                return false;

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++)
            if (!comparisonBase.getPlayer().getValue().equals(
                    cells[i].getPlayer().getValue()
            ))
                return false;

        return true;
    }
}
