package com.packtpub.calculator;

public class Calculator {

    private static final int OP_ADD = 1;

    private static final int OP_SUB = 2;

    private static final int OP_MUL = 3;

    private static final int OP_DIV = 4;

    private final StringBuilder builder = new StringBuilder("0");

    private boolean fractional = false;

    private double memory = 0;

    private int operation = 0;

    public Calculator() {
    }

    private void digit(final char digit) {
        if(fractional) {
            if(builder.length() == 1) {
                builder.append('.');
            }

            digit(digit);
        } if("0".contentEquals(builder)) {
            builder.setCharAt(0, digit);
        } else {
            builder.append(digit);
        }
    }

    private void calc(final double value) {
        switch(operation) {
            case 0:
                memory = value;
                break;
            case OP_ADD:
                memory += value;
                break;
            case OP_SUB:
                memory -= value;
                break;
            case OP_MUL:
                memory *= value;
                break;
            case OP_DIV:
                memory /= value;
                break;
        }
    }

    private void operation(final int op) {
        calc(Double.parseDouble(builder.toString()));
        operation = op;

        builder.setLength(1);
        builder.setCharAt(0, '0');
    }

    public void zero() {
        if(fractional) {
            if(builder.length() == 1) {
                builder.append('.');
            }
            
            builder.append('0');
        } else if(!"0".contentEquals(builder)) {
            builder.append('0');
        }
    }

    public void one() {
        digit('1');
    }

    public void two() {
        digit('2');
    }

    public void three() {
        digit('3');
    }

    public void four() {
        digit('4');
    }

    public void five() {
        digit('5');
    }

    public void six() {
        digit('6');
    }

    public void seven() {
        digit('7');
    }

    public void eight() {
        digit('8');
    }

    public void nine() {
        digit('9');
    }

    public void delete() {
        builder.deleteCharAt(builder.length() - 1);
        
        if(builder.length() == 0) {
            builder.append('0');
        }
    }

    public void add() {
        operation(OP_ADD);
    }

    public void subtract() {
        operation(OP_SUB);
    }

    public void multiply() {
        operation(OP_MUL);
    }

    public void divide() {
        operation(OP_DIV);
    }

    public CharSequence getCurrentDisplay() {
        return builder;
    }

}
