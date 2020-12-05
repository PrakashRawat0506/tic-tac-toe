package com.example.tictactoe.model;

/*
 * Class reprecenting different cells on a classic Tic Tac Toe game board
 * of dimensions 3x3
 */
public class Cell {

    private Player player;

    public Cell(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //Checking wheather a value has been entered into a cell or the cell is empty
    public boolean isEmpty() {
        return player == null || player.getValue() == null || player.getValue().trim().isEmpty();
    }
}
