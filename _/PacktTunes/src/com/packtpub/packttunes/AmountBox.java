package com.packtpub.packttunes;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import java.text.DecimalFormat;

public class AmountBox extends TextSwitcher
        implements ViewSwitcher.ViewFactory {

    private DecimalFormat format = new DecimalFormat("0.##");

    private double amount;

    public AmountBox(
            final Context context,
            final AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public AmountBox(final Context context) {
        super(context);
        init();
    }

    private void init() {
        setOutAnimation(getContext(), android.R.anim.fade_out);
        setInAnimation(getContext(), android.R.anim.fade_in);
        setFactory(this);
        setAmount(0);
    }

    public void setFormat(final DecimalFormat format) {
        this.format = format;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double value) {
        final int color;

        if(value < amount) {
            color = 0xff00ff00;
        } else if(value > amount) {
            color = 0xffff0000;
        } else {
            return;
        }

        final TextView offscreen = (TextView)getNextView();
        offscreen.setTextColor(color);
        offscreen.setShadowLayer(3, 0, 0, color);
        offscreen.setText(format.format(value));

        showNext();
        amount = value;
    }

    public View makeView() {
        final TextView view = new TextView(getContext());
        view.setTextSize(18);
        return view;
    }

}
