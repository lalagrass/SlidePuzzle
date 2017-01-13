package com.lalagrass.slidepuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lalagrass.slidepuzzle.MyClass.GridLayoutButton;
import com.lalagrass.slidepuzzle.MyClass.PuzzleLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button _bRandom;
    PuzzleLayout _puzzleLayout;
    TextView _tStep;
    private int _step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _tStep = (TextView) this.findViewById(R.id.tSteps);
        _puzzleLayout = (PuzzleLayout) this.findViewById(R.id.puzzleLayout);
        _puzzleLayout.setOnPuzzleListener(new PuzzleLayout.OnPuzzleListener() {
            @Override
            public void onStep() {
                StepIncr();
            }

            @Override
            public void onFinish() {

            }
        });
        _puzzleLayout.InitTouch();
        _bRandom = (Button) this.findViewById(R.id.bRandom);
        _bRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepReset();
                _puzzleLayout.DoRandom();
            }
        });
    }

    void StepIncr()
    {
        _step++;
        _tStep.setText(String.valueOf(_step));
    }

    void StepReset()
    {
        _step = 0;
        _tStep.setText(String.valueOf(_step));
    }
}
