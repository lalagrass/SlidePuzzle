package com.lalagrass.slidepuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.lalagrass.slidepuzzle.MyClass.GridLayoutButton;
import com.lalagrass.slidepuzzle.MyClass.PuzzleLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button _bRandom;
    PuzzleLayout _puzzleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _puzzleLayout = (PuzzleLayout) this.findViewById(R.id.puzzleLayout);
        _puzzleLayout.InitTouch();
        _bRandom = (Button) this.findViewById(R.id.bRandom);
        _bRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _puzzleLayout.DoRandom();
            }
        });
    }
}
