package com.lalagrass.slidepuzzle;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lalagrass.slidepuzzle.MyClass.GridLayoutButton;
import com.lalagrass.slidepuzzle.MyClass.PuzzleLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    boolean _gameRunning = false;
    Button _bRandom;
    PuzzleLayout _puzzleLayout;
    TextView _tStep;
    TextView _tClock;
    private int _step;
    private long _timerCount = 0;
    Timer _timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _timer = new Timer();
        _tStep = (TextView) this.findViewById(R.id.tSteps);
        _tClock = (TextView) this.findViewById(R.id.tClock);
        _puzzleLayout = (PuzzleLayout) this.findViewById(R.id.puzzleLayout);
        _puzzleLayout.setOnPuzzleListener(new PuzzleLayout.OnPuzzleListener() {
            @Override
            public void onStep() {
                StepIncr();
            }

            @Override
            public void onFinish() {
                _gameRunning = false;
                StopTime();
                ShowSuccess();
            }
        });
        _puzzleLayout.InitTouch();
        _bRandom = (Button) this.findViewById(R.id.bRandom);
        _bRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
                _puzzleLayout.DoRandom();
                Reset();
                if (!_gameRunning) {
                    _gameRunning = true;
                    StartTime();
                    _puzzleLayout.GameStart();
                }
            }
        });
        Reset();
    }

    void StepIncr() {
        _step++;
        _tStep.setText(String.valueOf(_step));
    }

    void StepReset() {
        _step = 0;
        _tStep.setText(String.valueOf(_step));

    }

    void StartTime()
    {
        if (_timer == null)
        {
            _timer = new Timer();
        }
        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                _tClock.post(new Runnable() {
                    @Override
                    public void run() {
                        TickTime();
                    }
                });
            }
        }, 100, 100);
    }

    void StopTime()
    {
        if (_timer != null)
        {
            _timer.cancel();
            _timer = null;
        }
    }

    void ShowTime(long timer) {

        _tClock.setText(GetTimeString(timer));
    }

    String GetTimeString(long timer)
    {
        int s1 = (int) (timer % 10);
        int s2 = (int) (timer / 10);
        int s3 = s2 % 60;
        int s4 = s2 / 60;
        return String.format("%02d:%02d.%d", s4, s3, s1);
    }

    void TickTime()
    {
        _timerCount++;
        ShowTime(_timerCount);
    }

    void TimeReset()
    {
        ShowTime(0);
        _timerCount = 0;
    }

    void Reset()
    {
        _gameRunning = false;
        StopTime();
        TimeReset();
        StepReset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StopTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (_gameRunning)
        {
            StartTime();
        }
    }

    private void ShowSuccess()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(GetTimeString(_timerCount)+ "\n" + _step)
                .setTitle("You Did it");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
