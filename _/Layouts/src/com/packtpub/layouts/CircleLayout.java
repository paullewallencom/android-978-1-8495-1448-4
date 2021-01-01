package com.packtpub.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CircleLayout extends ViewGroup {

    public CircleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleLayout(Context context) {
        super(context);
    }

    private int[] measureChildrenSizes(
            final int sw,
            final int sh) {

        int maxWidth = 0;
        int maxHeight = 0;

        for(int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            measureChild(child, sw, sh);

            maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }

        return new int[]{maxWidth, maxHeight};
    }

    @Override
    protected void onLayout(
            final boolean changed,
            final int l,
            final int t,
            final int r,
            final int b) {

        int w = r - l;
        int h = b - t;

        final int count = getChildCount();
        final int[] max = measureChildrenSizes(w, h);

        w -= max[0];
        h -= max[1];

        final int cx = w / 2;
        final int cy = h / 2;

        for(int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            final double v = 2 * Math.PI * i / count;
            final int x = l + (cx + (int)(Math.cos(v) * cx));
            final int y = t + (cy + (int)(Math.sin(v) * cy));

            child.layout(
                    x,
                    y,
                    x + child.getMeasuredWidth(),
                    y + child.getMeasuredHeight());
        }
    }

}
