package com.example.istminesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements OnCellClickListener {

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();


        }
    };
    public static long TIMER_LENGTH = 999000L;    // 999 seconds
    public static  int BOMB_COUNT = 10;
    public static  int GRID_SIZE = 10;



    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private RecyclerView grid;
    private TextView smiley, timer, flag, flagsLeft;
    public static MineSweeperGame mineSweeperGame;
    private CountDownTimer countDownTimer;
    private int secondsElapsed;
    private boolean timerStarted;

    public static boolean isflaget()
    {
        return  mineSweeperGame.isFlagMode();
    }




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        int  count_of_size =Integer.parseInt( extras.getString("size"));
        int  count_of_mines =Integer.parseInt( extras.getString("mines"));
        int  count_of_time =Integer.parseInt( extras.getString("time"));
        TIMER_LENGTH = count_of_time * 1000;
        grid = findViewById(R.id.activity_main_grid);
        GRID_SIZE = count_of_size;
        BOMB_COUNT = count_of_mines;
        grid.setLayoutManager(new GridLayoutManager(this, GRID_SIZE));
            Button back = findViewById(R.id.button2);
            back.setOnClickListener(btnClick);


        timer = findViewById(R.id.activity_main_timer);
        timerStarted = false;
        countDownTimer = new CountDownTimer (TIMER_LENGTH, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            public void onFinish() {
                mineSweeperGame.outOfTime();
                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_SHORT).show();
                mineSweeperGame.getMineGrid().revealAllBombs();
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
            }
        };

        flagsLeft = findViewById(R.id.activity_main_flagsleft);

        mineSweeperGame = new MineSweeperGame(GRID_SIZE, BOMB_COUNT);
        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() ));
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(mineSweeperGame.getMineGrid().getCells(), this);
        grid.setAdapter(mineGridRecyclerAdapter);

        smiley = findViewById(R.id.activity_main_smiley);
        smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperGame = new MineSweeperGame(GRID_SIZE, BOMB_COUNT);
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
                timerStarted = false;
                countDownTimer.cancel();
                secondsElapsed = 0;
                timer.setText(R.string.default_count);
                flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() ));
                flag = findViewById(R.id.activity_main_flag);
                flag.setText(R.string.flag);
            }
        });

        flag = findViewById(R.id.activity_main_flag);
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperGame.toggleMode();
                if (mineSweeperGame.isFlagMode()) {
                    // GradientDrawable border = new GradientDrawable();
                    //border.setColor(0xFF0000);
                 //   border.setStroke(1, 0xFF000000);
                  //  flag.setBackground(border);
                    flag.setText(R.string.finger);
                } else {
                  //  GradientDrawable border = new GradientDrawable();
                  //  border.setColor(0xFFFFFFFF);
                  //  flag.setBackground(border);
                    flag.setText(R.string.flag);
                }
            }
        });
    }

    @Override
    public void cellClick(Cell cell) {

        mineSweeperGame.handleCellClick(cell);

        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() ));

        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
        }

        if (mineSweeperGame.isGameOver()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        if (mineSweeperGame.isGameWon()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Won", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }
}