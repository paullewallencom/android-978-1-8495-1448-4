package com.packtpub.calculator;

import android.app.Activity;

import android.os.Bundle;
import android.view.KeyEvent;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Queue;
import java.util.LinkedList;

public class CalculatorActivity extends Activity implements OnClickListener {

    private ViewSwitcher switcher;

    private TextView display;

    private Calculator calculator;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        calculator = new Calculator();

        switcher = (ViewSwitcher)findViewById(R.id.display);
        this.display = (TextView)switcher.getCurrentView();
        
        findViewById(R.id.delete).setOnClickListener(this);

        final ViewGroup standard = (ViewGroup)findViewById(R.id.standard_functions);
        final ViewGroup scientific = (ViewGroup)findViewById(R.id.scientific_functions);

        final Queue<ViewGroup> views = new LinkedList<ViewGroup>();
        views.add(standard);

        if(scientific != null) {
            views.add(scientific);
        }

        while(!views.isEmpty()) {
            final ViewGroup g = views.poll();
            final int children = g.getChildCount();

            for(int i = 0; i < children; i++) {
                final View v = g.getChildAt(i);

                if(v instanceof ViewGroup) {
                    views.add((ViewGroup)v);
                } else if(v instanceof Button) {
                    v.setOnClickListener(this);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(
            final int keyCode,
            final KeyEvent event) {

        super.onKeyDown(keyCode, event);

        boolean handled = false;

        if((event.getFlags() & KeyEvent.FLAG_SOFT_KEYBOARD) == 0) {
            switch(keyCode) {
                case KeyEvent.KEYCODE_0:
                    calculator.zero();
                    handled = true;
                    break;
                case KeyEvent.KEYCODE_1:
                    calculator.one();
                    handled = true;
                    break;
            }

            display.setText(calculator.getCurrentDisplay());
        }

        return handled;
    }

    public void onClick(final View clicked) {
        boolean operation = false;

        switch(clicked.getId()) {
            case R.id.zero:
                calculator.zero();
                break;
            case R.id.one:
                calculator.one();
                break;
            case R.id.two:
                calculator.two();
                break;
            case R.id.three:
                calculator.three();
                break;
            case R.id.four:
                calculator.four();
                break;
            case R.id.five:
                calculator.five();
                break;
            case R.id.six:
                calculator.six();
                break;
            case R.id.seven:
                calculator.seven();
                break;
            case R.id.eight:
                calculator.eight();
                break;
            case R.id.nine:
                calculator.nine();
                break;
            case R.id.delete:
                calculator.delete();
                break;
            case R.id.plus:
                calculator.add();
                operation = true;
                break;
            case R.id.minus:
                calculator.subtract();
                operation = true;
                break;
            case R.id.multiply:
                calculator.multiply();
                operation = true;
                break;
            case R.id.divide:
                calculator.divide();
                operation = true;
                break;
        }

        if(operation) {
            display = (TextView)switcher.getNextView();
        }

        display.setText(calculator.getCurrentDisplay());

        if(operation) {
            switcher.showNext();
        }
    }
}
