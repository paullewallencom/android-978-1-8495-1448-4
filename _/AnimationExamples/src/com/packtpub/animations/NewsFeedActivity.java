package com.packtpub.animations;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class NewsFeedActivity extends Activity implements Runnable {

    private final Handler handler = new Handler();

    private TextSwitcher newsFeed;

    private String[] headlines;

    private int headlineIndex;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        headlines = getResources().getStringArray(R.array.headlines);
        newsFeed = (TextSwitcher)findViewById(R.id.news_feed);
        newsFeed.setFactory(new ViewFactory() {

            public View makeView() {
                return new TextView(NewsFeedActivity.this);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        headlineIndex = 0;
        handler.postDelayed(this, 3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(this);
    }

    public void run() {
        try {
            newsFeed.setText(headlines[headlineIndex++]);

            if(headlineIndex >= headlines.length) {
                headlineIndex = 0;
            }
        } finally {
            handler.postDelayed(this, 3000);
        }
    }

}
