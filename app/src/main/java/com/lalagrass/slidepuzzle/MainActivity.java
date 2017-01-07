package com.lalagrass.slidepuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.lalagrass.slidepuzzle.MyClass.GridLayoutButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridLayoutButton[] _b;
    GridLayoutButton _bSpace;
    Button _bRandom;
    int _lastRandom = 1;
    boolean _isRandoming = false;

    enum EnumRanState {
        WriteTrue,
        WriteFalse,
        Read
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _bRandom = (Button) this.findViewById(R.id.bRandom);
        _b = new GridLayoutButton[8];
        _b[0] = (GridLayoutButton) this.findViewById(R.id.b1);
        _b[1] = (GridLayoutButton) this.findViewById(R.id.b2);
        _b[2] = (GridLayoutButton) this.findViewById(R.id.b3);
        _b[3] = (GridLayoutButton) this.findViewById(R.id.b4);
        _b[4] = (GridLayoutButton) this.findViewById(R.id.b5);
        _b[5] = (GridLayoutButton) this.findViewById(R.id.b6);
        _b[6] = (GridLayoutButton) this.findViewById(R.id.b7);
        _b[7] = (GridLayoutButton) this.findViewById(R.id.b8);
        _bSpace = (GridLayoutButton) this.findViewById(R.id.b9);
        Reset();

        for (int i = 0; i < 8; i++) {
            _b[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GridLayoutButton b = (GridLayoutButton) v;
                    if (b != null) {
                        if (!IsRandoming(EnumRanState.Read))
                            TestAndSwitch(b);
                    }
                }
            });
        }

        _bRandom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        IsRandoming(EnumRanState.WriteTrue);
                        break;
                    default:
                        IsRandoming(EnumRanState.WriteFalse);
                        break;
                }
                return true;
            }
        });
    }

    void Reset() {
        _lastRandom = 3;
        _b[0].SetPosition(0, 0);
        _b[1].SetPosition(0, 1);
        _b[2].SetPosition(0, 2);
        _b[3].SetPosition(1, 0);
        _b[4].SetPosition(1, 1);
        _b[5].SetPosition(1, 2);
        _b[6].SetPosition(2, 0);
        _b[7].SetPosition(2, 1);
        _bSpace.SetPosition(2, 2);
    }

    void DoRandom() {
        Random r = new Random();
        int i = r.nextInt(4);
        switch (i) {
            case 0:
                if (_lastRandom != 2)
                    TestAndSwitch(_bSpace.Column - 1, _bSpace.Row);
                break;
            case 1:
                if (_lastRandom != 3)
                    TestAndSwitch(_bSpace.Column, _bSpace.Row + 1);
                break;
            case 2:
                if (_lastRandom != 0)
                    TestAndSwitch(_bSpace.Column + 1, _bSpace.Row);
                break;
            case 3:
                if (_lastRandom != 1)
                    TestAndSwitch(_bSpace.Column, _bSpace.Row - 1);
                break;
        }
        _lastRandom = i;
    }

    synchronized boolean IsRandoming(EnumRanState state) {
        switch (state) {
            case WriteTrue:
                if (_isRandoming != true)
                    _isRandoming = true;
                DoRandom();
                break;
            case WriteFalse:
                if (_isRandoming != false)
                    _isRandoming = false;
                break;
            default:
                break;
        }
        return _isRandoming;
    }

    void TestAndSwitch(int r, int c) {
        GridLayoutButton b = null;
        for (int i = 0; i < 8; i++) {
            if (_b[i].Row == r && _b[i].Column == c) {
                b = _b[i];
                break;
            }
        }
        TestAndSwitch(b);
    }

    synchronized void TestAndSwitch(GridLayoutButton b) {
        if (b != null && _bSpace.GetDistance(b) == 1) {
            int tmpR = _bSpace.Row;
            int tmpC = _bSpace.Column;
            _bSpace.SetPosition(b.Row, b.Column);
            b.SetPosition(tmpR, tmpC);
        }
    }
}
