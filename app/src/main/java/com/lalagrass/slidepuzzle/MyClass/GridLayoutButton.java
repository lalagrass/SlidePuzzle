package com.lalagrass.slidepuzzle.MyClass;

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.widget.Button;

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

    public void SetPosition(int r, int c) {
        Row = r;
        Column = c;
        GridLayout.LayoutParams params = (GridLayout.LayoutParams) this.getLayoutParams();
        params.columnSpec = GridLayout.spec(c, 1, 1);
        params.rowSpec = GridLayout.spec(r, 1, 1);
        this.setLayoutParams(params);
    }

    public int GetDistance(GridLayoutButton b) {
        return Math.abs(b.Row - Row) + Math.abs(b.Column - Column);
    }
}
