package com.example.data;

import com.example.domain.Cell;
import com.example.domain.Game;
import com.example.domain.Player;

import java.util.HashMap;
import java.util.Map;

public class GameModel {

    public Map<String, String> cells;

    private Game game;

    public void init(Game game) {
        this.game = game;
        cells = new HashMap<>();
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

    public LiveData<Player> getWinner() {
        return game.getWinner();
    }
}
