package com.example.cosmin.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0 - yellow player, 1 - red player
    int playerState = 0;

    int[] checkState = {2,2,2,2,2,2,2,2,2};

    int[][] winCondition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean blockPlay = false;


    public void diveIn(View view) {


        ImageView counter = (ImageView) view;

        int checkPressed = Integer.parseInt(counter.getTag().toString());

        if (checkState[checkPressed] == 2 && !blockPlay) {

            checkState[checkPressed] = playerState;

            if (playerState == 0) {

                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).setDuration(300);
                playerState = 1;
            } else {
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).setDuration(300);
                playerState = 0;
            }

        }

        for (int[] winPos : winCondition) {
            if (checkState[winPos[0]] == checkState[winPos[1]] &&
                    checkState[winPos[1]] == checkState[winPos[2]] && checkState[winPos[0]] != 2) {
                //you win, game over
                //afisez layout-ul invizibil cu numele castigatorului

                blockPlay = true;

                LinearLayout gameOver = (LinearLayout) findViewById(R.id.gameOver);
                TextView nume = (TextView) findViewById(R.id.gameOverTxt);
                String setNume = "";
                if (playerState == 0) {
                    setNume = "YELLOW WINS";
                }
                else {
                    setNume = "RED WINS";
                }

                nume.setText(setNume);

                gameOver.setVisibility(View.VISIBLE);
            }
            else {
                boolean fullGrid = true;

                for (int i : checkState) {
                    if (i == 2) {
                        fullGrid = false;
                    }
                }

                if (fullGrid) {
                    blockPlay = true;

                    LinearLayout gameOver = (LinearLayout) findViewById(R.id.gameOver);
                    TextView nume = (TextView) findViewById(R.id.gameOverTxt);
                    nume.setText("ITS A DRAW!");
                    gameOver.setVisibility(View.VISIBLE);
                }
            }


        }

    }

    public void restart(View view) {
        blockPlay = false;

        LinearLayout gameOver = (LinearLayout) findViewById(R.id.gameOver);
        gameOver.setVisibility(View.INVISIBLE);
        playerState = 0;
        for (int i = 0; i < checkState.length; i++) {
            checkState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout2);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

}
