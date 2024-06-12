package com.harsha.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    Button restartGameBtn;
    TextView mTextView;

    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int activePlayer = PLAYER_O;
    boolean isGameActive = true;
    int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning the address of the Buttons
        mTextView = (TextView) findViewById(R.id.UserTurnTxt);
        mButton0 = (Button) findViewById(R.id.Button0);
        mButton1 = (Button) findViewById(R.id.Button1);
        mButton2 = (Button) findViewById(R.id.Button2);
        mButton3 = (Button) findViewById(R.id.Button3);
        mButton4 = (Button) findViewById(R.id.Button4);
        mButton5 = (Button) findViewById(R.id.Button5);
        mButton6 = (Button) findViewById(R.id.Button6);
        mButton7 = (Button) findViewById(R.id.Button7);
        mButton8 = (Button) findViewById(R.id.Button8);
        restartGameBtn = (Button) findViewById(R.id.RestartGameBtn);

        //Onclick Listener to get Button tag and set Text to it.
        mButton0.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);


        //Implementing onClickListener to restart the game
        restartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }

    //Calling when the user clicks on Any Button
    @Override
    public void onClick(View view) {

        //if Game is not Active we will do nothing
        if (!isGameActive)
            return;

        //getting buttons id
        Button clickedBtn = findViewById(view.getId());

        //Getting Tag of the clicked button
        int clickedTag = Integer.parseInt(view.getTag().toString());

        //if the user clicks the Already clicked button we will do nothing
        if (filledPositions[clickedTag] != -1) {
            //Do nothing
            return;

        }
        //we will set update the value in array with the Constant value of user
        filledPositions[clickedTag] = activePlayer;

        //if the Active player is O we will set the button text to O and change color and we will set next user as X
        if (activePlayer == PLAYER_O) {
            clickedBtn.setText("o");
            clickedBtn.setBackgroundColor(Color.RED);
            activePlayer = PLAYER_X;
            mTextView.setText("Player X-Turn");
        } else {
            clickedBtn.setText("x");
            activePlayer = PLAYER_O;
            clickedBtn.setBackgroundColor(Color.GREEN);
            mTextView.setText("Player O-Turn");
        }

        //check for winner
        checkWinner();

        //check for Draw
        checkDraw();
    }

    private void checkDraw() {
        int count = 0;

        //we will check all the values in the array and increase count if it is not equal to default value
        for (int i = 0; i < 9; i++) {
            if (filledPositions[i] != -1) {
                count++;
            }
        }
        //if all the values in array are not equal to default value  we will show as the game is draw
        if (count == 9) {
            //Check for whether any player won the game or not before showing draw
            if (checkWinner())
                return;
            showWinner("Game is Draw");
            isGameActive = false;  //setting the game to inactive
        }
    }

    private boolean checkWinner() {

        //Assigning all the possible ways to win in an Array
        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        //Getting all the winning positions
        for (int i = 0; i < 8; i++) {
            int value0 = winningPosition[i][0];
            int value1 = winningPosition[i][1];
            int value2 = winningPosition[i][2];

            //checking whether the value is equal to default value or not
            if (filledPositions[value0] != -1) {
                if (filledPositions[value0] == filledPositions[value1] && filledPositions[value1] == filledPositions[value2]) {

                    //Making the game inActive
                    isGameActive = false;

                    //Deciding winner
                    if (filledPositions[value0] == PLAYER_O) {
                        showWinner("Winner is Player O");

                    } else {
                        showWinner("Winner is Player X");
                    }
                    //Returning true to prevent showing the result form draw
                    return true;
                }


            }
        }
        return false;
    }

    private void showWinner(String winner) {

        //Creating a Alert Dialog to show the winner and for Restarting the Game
        new AlertDialog.Builder(this)
                .setTitle(winner)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                }).show();
    }

    private void restartGame() {

        //Setting the default palyer to Player O
        activePlayer = PLAYER_O;

        //Setting the all the values to default
        filledPositions = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};

        //Setting all the Text on the buttons to empty
        mButton0.setText("");
        mButton1.setText("");
        mButton2.setText("");
        mButton3.setText("");
        mButton4.setText("");
        mButton5.setText("");
        mButton6.setText("");
        mButton7.setText("");
        mButton8.setText("");

        //setting the default color for buttons
        mButton0.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton1.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton2.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton3.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton4.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton5.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton6.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton7.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton8.setBackgroundColor(Color.parseColor("#BFBFBF"));

        //showing the text as player O's turn
        mTextView.setText("Player O Turn");

        //making the game active again
        isGameActive = true;
    }
}