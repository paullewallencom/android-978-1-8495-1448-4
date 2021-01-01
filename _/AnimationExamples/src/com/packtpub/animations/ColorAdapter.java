package com.packtpub.animations;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.AbsListView.LayoutParams;

public class ColorAdapter extends BaseAdapter {

    private final int rows;

    private final int cols;

    public ColorAdapter(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getCount() {
        return rows * cols;
    }

    public long getItemId(final int pos) {
        return pos;
    }

    private int getColor(final int pos) {
        final float h = (float)pos / (float)getCount();
        return Color.HSVToColor(new float[]{h * 360f, 1f, 1f});
    }

    public Object getItem(final int pos) {
        return getColor(pos);
    }

    public View getView(final int pos, final View reuse, final ViewGroup parent) {
        final ImageView view = reuse instanceof ImageView
                ? (ImageView)reuse
                : new ImageView(parent.getContext());

        view.setImageDrawable(new ColorDrawable(getColor(pos)));
        view.setLayoutParams(new LayoutParams(16, 16));

        return view;
    }

}
