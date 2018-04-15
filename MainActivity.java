package com.example.nysil.tictactoe;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewPlayerOne, textViewPlayerTwo;
    private Button[][] buttons = new Button[3][3];
    private boolean playerOneTurn = true;
    private int roundCount;
    private int playerOnePoints;
    private int playerTwoPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing both the player score textviews
        textViewPlayerOne = (TextView)findViewById(R.id.textView1);
        textViewPlayerTwo = (TextView)findViewById(R.id.textView2);

        //now initializing buttons to our buttons
        //using for loop to do that dynamically

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                String buttonID="button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset= findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetBoard();
                textViewPlayerOne.setText("Player One: 0");
                textViewPlayerTwo.setText("Player Two: 0");

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(playerOneTurn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }
        roundCount++;

        if(checkForWin())
        {
            if(playerOneTurn)
            {
                playerOneWins();
            }
            else
            {
                playerTwoWins();
            }
        }
        else if(roundCount == 9)
        {
            draw();
        }
        else
        {
            playerOneTurn = !playerOneTurn;
        }
    }

    private void playerOneWins()
    {
        playerOnePoints++;
        Toast.makeText(this, "Player One Wins", Toast.LENGTH_SHORT).show();

        updatePointsText();
        resetBoard();
    }

    private void playerTwoWins()
    {
        playerTwoPoints++;
        Toast.makeText(this, "Player Two Wins", Toast.LENGTH_SHORT).show();

        updatePointsText();
        resetBoard();

    }

    private void updatePointsText() {

        textViewPlayerOne.setText("Player One: "+ playerOnePoints);
        textViewPlayerTwo.setText("Player Two: "+ playerTwoPoints);
    }

    private void draw()
    {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard() {
        for(int i=0; i<3; i++)
        {
            for( int j=0; j<3; j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        playerOneTurn = true;
    }

    private boolean checkForWin()
    {
        String[][] field = new String[3][3];

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0; i<3; i++)
        {
            if(field[i][0].equals(field[i][1]) &&
                    field[i][0].equals(field[i][2]) &&
                    !field[i][0].equals(""))
            {
                return true;
            }
        }

        for(int i=0; i<3; i++)
        {
            if(field[0][i].equals(field[1][i]) &&
                    field[0][i].equals(field[2][i]) &&
                    !field[0][i].equals(""))
            {
                return  true;
            }
        }

        if(field[0][0].equals(field[1][1]) &&
                field[0][0].equals(field[2][2]) &&
                !field[0][0].equals(""))
        {
            return true;
        }

        if(field[0][2].equals(field[1][1]) &&
                field[0][2].equals(field[2][0]) &&
                !field[0][2].equals(""))
        {
            return true;
        }

        return false;
    }
}
