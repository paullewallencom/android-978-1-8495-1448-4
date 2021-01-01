/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.validation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

/**
 * <p>
 * </p><p>
 * Created on 25 Sep 2010
 * </p>
 *
 * @author Jason Morris
 */
public class ValidatingDatePickerDialog extends DatePickerDialog {

    private int lastValidYear;
    private int lastValidMonth;
    private int lastValidDay;
    private ValidationCallback callback = null;

    public ValidatingDatePickerDialog(
            final Context context,
            final int theme,
            final OnDateSetListener callBack,
            final int year,
            final int monthOfYear,
            final int dayOfMonth) {

        super(context, callBack, year, monthOfYear, dayOfMonth);
        setValidData(year, monthOfYear, dayOfMonth);
    }

    public ValidatingDatePickerDialog(
            final Context context,
            final OnDateSetListener callBack,
            final int year,
            final int monthOfYear,
            final int dayOfMonth) {

        super(context, year, callBack, year, monthOfYear, dayOfMonth);
        setValidData(year, monthOfYear, dayOfMonth);
    }

    protected void setValidData(
            final int year,
            final int monthOfYear,
            final int dayOfMonth) {

        lastValidYear = year;
        lastValidMonth = monthOfYear;
        lastValidDay = dayOfMonth;
    }

    @Override
    public void onDateChanged(
            final DatePicker view,
            final int year,
            final int month,
            final int day) {

        if(callback != null && !callback.isValid(year, month, day)) {
            view.updateDate(
                    lastValidYear,
                    lastValidMonth,
                    lastValidDay);
        } else {
            super.onDateChanged(view, year, month, day);
            setValidData(year, month, day);
        }
    }

    public void setValidationCallback(final ValidationCallback callback) {
        this.callback = callback;
    }

    public ValidationCallback getValidationCallback() {
        return callback;
    }

    public interface ValidationCallback {
        boolean isValid(int year, int monthOfYear, int dayOfMonth);
    }
}
