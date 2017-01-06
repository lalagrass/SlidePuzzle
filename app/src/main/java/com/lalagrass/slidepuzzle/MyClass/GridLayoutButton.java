package com.lalagrass.slidepuzzle.MyClass;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.Button;

/**
 * Created by ASUS on 1/6/2017.
 */

public class GridLayoutButton extends Button {

    public int Row;
    public int Column;

    public GridLayoutButton(Context context) {
        super(context);
    }

    public GridLayoutButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridLayoutButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void SetPosition(int r, int c)
    {
        Row = r;
        Column = c;
    }

    public Pair<Integer, Integer> GetPosition()
    {
        return new Pair<>(Row, Column);
    }

    public int GetDistance(Pair<Integer, Integer> pos)
    {
        return Math.abs(pos.first - Row) + Math.abs(pos.second - Column);
    }
}
