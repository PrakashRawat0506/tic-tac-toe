package com.example.tictactoe;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

/*
 * Dialog to be shown at the end of the game
 */
public class GameEndDialog extends AppCompatDialogFragment {
    private View rootView;
    private com.example.tictactoe.MainActivity activity;
    private String winnerName;

    //Creating single instance for the fragment
    public static com.example.tictactoe.GameEndDialog newInstance(com.example.tictactoe.MainActivity activity, String winnerName) {
        com.example.tictactoe.GameEndDialog dialog = new com.example.tictactoe.GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.all_done, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    //initializing fragment views
    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_gameend_dialog, null, false);
        ((TextView) rootView.findViewById(R.id.textViewGameEndDialogTextViewWinnerName)).setText(winnerName);
    }

    //Dismissing the dialog and show the GameBeginDialog to the user
    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }
}
