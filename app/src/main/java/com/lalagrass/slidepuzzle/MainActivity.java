package com.lalagrass.slidepuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Space;

import com.lalagrass.slidepuzzle.MyClass.GridLayoutButton;

public class MainActivity extends AppCompatActivity {

    GridLayoutButton[] _b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _b = new GridLayoutButton[9];
        _b[0] = (GridLayoutButton) this.findViewById(R.id.b1);
        _b[1] = (GridLayoutButton) this.findViewById(R.id.b2);
        _b[2] = (GridLayoutButton) this.findViewById(R.id.b3);
        _b[3] = (GridLayoutButton) this.findViewById(R.id.b4);
        _b[4] = (GridLayoutButton) this.findViewById(R.id.b5);
        _b[5] = (GridLayoutButton) this.findViewById(R.id.b6);
        _b[6] = (GridLayoutButton) this.findViewById(R.id.b7);
        _b[7] = (GridLayoutButton) this.findViewById(R.id.b8);
        _b[8] = (GridLayoutButton) this.findViewById(R.id.b9);

        _b[0].SetPosition(0, 0);
        _b[1].SetPosition(0, 1);
        _b[2].SetPosition(0, 2);
        _b[3].SetPosition(1, 0);
        _b[4].SetPosition(1, 1);
        _b[5].SetPosition(1, 2);
        _b[6].SetPosition(2, 0);
        _b[7].SetPosition(2, 1);
        _b[8].SetPosition(2, 2);
        for (int i = 0; i < 8; i++)
        {
            _b[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GridLayoutButton b = (GridLayoutButton) v;
                    if (b != null)
                    {
                        TestAndSwitch(b);
                    }
                }
            });
        }
    }

    synchronized void TestAndSwitch(GridLayoutButton b)
    {
        if (_b[8].GetDistance(b.GetPosition()) == 1)
        {
            int tmpR = _b[8].Row;
            int tmpC = _b[8].Column;

            GridLayout.LayoutParams p0 = (GridLayout.LayoutParams) _b[8].getLayoutParams();
            GridLayout.LayoutParams p1 = (GridLayout.LayoutParams) b.getLayoutParams();

            _b[8].setLayoutParams(p1);
            _b[8].SetPosition(b.Row, b.Column);

            b.setLayoutParams(p0);
            b.SetPosition(tmpR, tmpC);
        }
    }
}
