package com.example.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tictactoe.dagger.DaggerGameComponent;
import com.example.tictactoe.dagger.GameComponent;
import com.example.tictactoe.databinding.ActivityMainBinding;
import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.Player;
import com.example.tictactoe.utitlity.StringUtility;
import com.example.tictactoe.viewmodel.GameViewModel;

public class MainActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();

    }

    //Displaying the GameBeginDialog to the user
    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), Constants.GAME_BEGIN_DIALOG_TAG);
    }

    //Setting the values for both the players
    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    //Initializing the dagger component, ViewModel class and binding the views
    private void initDataBinding(String player1, String player2) {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        GameComponent component = DaggerGameComponent.builder()
                .name1(player1)
                .name2(player2)
                .build();
        Game game = component.getGame();
        gameViewModel = new GameViewModel(game);
        activityMainBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    //Observing the winner for changes
    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().subscribe(this::onGameWinnerChanged);
    }

    //Show GameEndDialog when the game finishes
    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || StringUtility.isNullOrEmpty(winner.getName()) ? Constants.NO_WINNER : winner.getName();
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), Constants.GAME_END_DIALOG_TAG);
    }
}