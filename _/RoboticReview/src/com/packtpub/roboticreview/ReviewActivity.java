/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.roboticreview;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Calendar;

import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher;

/**
 *
 * @author Jason Morris
 */
public class ReviewActivity extends TabActivity
        implements ViewSwitcher.ViewFactory,
        Runnable,
        AdapterView.OnItemSelectedListener,
        SeekBar.OnSeekBarChangeListener,
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    // the "review" tab fields
    private final Handler switchCommentHandler = new Handler();

    private String[] comments;

    private int commentIndex = 0;

    private TextSwitcher switcher;

    // the "photos" tab fields
    private ImageView photo;

    // the "reservation" tab fields
    private String peopleLabelFormat;

    private TextView peopleLabel;

    private SimpleDateFormat dateFormat;

    private Button date;

    private SimpleDateFormat timeFormat;

    private Button time;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        createTabs();
    }

    private void initReveiwTab() {
        comments = getResources().getStringArray(R.array.comments);

        switcher = (TextSwitcher)findViewById(R.id.reviews);
        switcher.setFactory(this);
    }

    private void initGalleryTab() {
        photo = ((ImageView)findViewById(R.id.photo));

        final Gallery photos = ((Gallery)findViewById(R.id.gallery));
        photos.setAdapter(new GalleryAdapter());
        photos.setOnItemSelectedListener(this);
    }

    private void initReservationTab() {
        peopleLabel = (TextView)findViewById(R.id.people_label);
        peopleLabelFormat = peopleLabel.getText().toString();

        date = (Button)findViewById(R.id.date);
        dateFormat = new SimpleDateFormat(date.getText().toString());

        time = (Button)findViewById(R.id.time);
        timeFormat = new SimpleDateFormat(time.getText().toString());

        final Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.HOUR_OF_DAY) >= 16) {
            calendar.add(Calendar.DATE, 1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        final Date reservationDate = calendar.getTime();

        date.setText(dateFormat.format(reservationDate));
        time.setText(timeFormat.format(reservationDate));
        date.setOnClickListener(this);
        time.setOnClickListener(this);

        final SeekBar people = (SeekBar)findViewById(R.id.people);

        people.setOnSeekBarChangeListener(this);

        peopleLabel.setText(String.format(
                peopleLabelFormat,
                people.getProgress() + 1));
    }

    private TabHost.TabSpec createTabSpec(
            final TabHost host,
            final String tag,
            final int content,
            final int textString,
            final int icon) {

        return host.newTabSpec(tag).
                setContent(content).
                setIndicator(
                getString(textString),
                getResources().getDrawable(icon));
    }

    private Calendar parseCalendar(
            final CharSequence text,
            final SimpleDateFormat format) {

        try {
            final Date parsedDate = format.parse(text.toString());
            final Calendar calendar = Calendar.getInstance();

            calendar.setTime(parsedDate);

            return calendar;
        } catch(final ParseException pe) {
            Log.e("DateParse", "Couldn't parse date: " + text, pe);
            throw new RuntimeException(pe);
        }
    }

    private void createTabs() {
        final TabHost tabs = getTabHost();

        getLayoutInflater().inflate(
                R.layout.main,
                tabs.getTabContentView(),
                true);

        tabs.addTab(createTabSpec(
                tabs,
                "review",
                R.id.review,
                R.string.review,
                R.drawable.review));

        tabs.addTab(createTabSpec(
                tabs,
                "photos",
                R.id.photos,
                R.string.gallery,
                R.drawable.photos));

        tabs.addTab(createTabSpec(
                tabs,
                "reservation",
                R.id.reservation,
                R.string.reservation,
                R.drawable.reservation));

        initReveiwTab();
        initGalleryTab();
        initReservationTab();
    }

    @Override
    protected void onStart() {
        super.onStart();
        switchCommentHandler.postDelayed(this, 5 * 1000l);
    }

    @Override
    protected void onStop() {
        super.onStop();
        switchCommentHandler.removeCallbacks(this);
    }

    public View makeView() {
        return getLayoutInflater().inflate(R.layout.review_comment, null);
    }

    public void run() {
        try {
            if(switcher != null) {
                switcher.setText(comments[commentIndex++]);

                if(commentIndex >= comments.length) {
                    commentIndex = 0;
                }
            }
        } finally {
            switchCommentHandler.postDelayed(this, 5 * 1000l);
        }
    }

    public void onItemSelected(
            final AdapterView<?> parent,
            final View view,
            final int index,
            final long id) {

        photo.setImageResource((int)id);
    }

    public void onNothingSelected(final AdapterView<?> view) {
        // ignore this
    }

    public void onProgressChanged(
            final SeekBar bar,
            final int progress,
            final boolean fromUser) {

        peopleLabel.setText(String.format(peopleLabelFormat, progress + 1));
    }

    public void onStartTrackingTouch(final SeekBar bar) {
    }

    public void onStopTrackingTouch(final SeekBar bar) {
    }

    public void onClick(final View view) {
        if(view == date) {
            final Calendar calendar = parseCalendar(
                    date.getText(),
                    dateFormat);

            new DatePickerDialog(
                    this,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        } else if(view == time) {
            final Calendar calendar = parseCalendar(
                    time.getText(),
                    timeFormat);

            new TimePickerDialog(
                    this,
                    this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false).show();
        }
    }

    public void onDateSet(
            final DatePicker picker,
            final int year,
            final int month,
            final int day) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        date.setText(dateFormat.format(calendar.getTime()));
    }

    public void onTimeSet(
            final TimePicker picker,
            final int hour,
            final int minute) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        time.setText(timeFormat.format(calendar.getTime()));
    }

}
