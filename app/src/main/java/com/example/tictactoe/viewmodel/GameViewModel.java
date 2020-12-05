package com.example.tictactoe.viewmodel;

import androidx.databinding.ObservableArrayMap;

import com.example.tictactoe.model.Cell;
import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.Player;
import com.example.tictactoe.utitlity.StringUtility;

import io.reactivex.rxjava3.subjects.Subject;

public class GameViewModel {

    public ObservableArrayMap<String, String> cells;

    private Game game;

    public GameViewModel(Game game) {
        this.game = game;
        cells = new ObservableArrayMap<>();
    }

    //Logic to perform when a user clicks on a cell
    public void onClickedCellAt(int row, int column) {
        if((game.getCells())[row][column] == null) {
            (game.getCells())[row][column] = new Cell(game.getCurrentPlayer());
            cells.put(StringUtility.stringFromNumbers(row,column),
                    game.getCurrentPlayer().getValue());
            if(game.hasGameEnded())

                //Reset all the values
                game.reset();
            else

                //Switch to other player
                game.switchPlayer();
        }
    }

    public Subject<Player> getWinner() { return game.getWinner(); }

}
