package com.example.tictactoe;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

/*
 * Dialog to be shown at the begining of the game
 * Both players are asked to enter their names
 */
public class GameBeginDialog extends AppCompatDialogFragment {

    private AppCompatEditText player1EditText;
    private AppCompatEditText player2EditText;

    private String player1;
    private String player2;

    private View rootView;
    private com.example.tictactoe.MainActivity activity;

    //Creating single instance for fragment
    public static com.example.tictactoe.GameBeginDialog newInstance(com.example.tictactoe.MainActivity activity) {
        com.example.tictactoe.GameBeginDialog dialog = new com.example.tictactoe.GameBeginDialog();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle(R.string.gameBeginDialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.all_done, null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> {
            onDialogShow(alertDialog);
        });
        return alertDialog;
    }

    //initializing fragment views
    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_gamebegin_dialog, null, false);

        player1EditText = rootView.findViewById(R.id.editTextGameBeginDialogEditTextPlayer1);
        player2EditText = rootView.findViewById(R.id.editTextGameBeginDialogEditTextPlayer2);

        //Adding watcher for player1EditText and player2EditText
        addTextWatchers();
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {

            //Action to be performed on button click
            onDoneClicked();
        });
    }

    private void onDoneClicked() {

        //Check whether entered player names are valid or not
        if (isAValidName(player1) & isAValidName(player2)) {
            activity.onPlayersSet(player1, player2);
            dismiss();
        }
    }

    //Logic for checking name entered by player is valid or not
    private boolean isAValidName(String name) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(activity, getResources().getText(R.string.gameBeginDialog_emptyName), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (player1 != null && player1.equalsIgnoreCase(player2)) {
            Toast.makeText(activity, getResources().getText(R.string.gameBeginDialog_sameNames), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //Custom text watcher for both the textViews
    private void addTextWatchers() {
        player1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                player1 = s.toString();
            }
        });
        player2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                player2 = s.toString();
            }
        });
    }
}
