package com.packtpub.animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AnimationSelector extends Activity implements OnClickListener {

    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main);

        ((Button)findViewById(R.id.news_feed)).setOnClickListener(this);
        ((Button)findViewById(R.id.colors)).setOnClickListener(this);
        ((Button)findViewById(R.id.vanish)).setOnClickListener(this);
    }

    public void onClick(final View view) {
        switch(view.getId()) {
            case R.id.news_feed:
                startActivity(new Intent(this, NewsFeedActivity.class));
                break;
            case R.id.colors:
                startActivity(new Intent(this, ColorSelectorActivity.class));
                break;
            case R.id.vanish:
                startActivity(new Intent(this, VanishingButtonActivity.class));
                break;
            default:
                throw new AssertionError();
        }
    }

}
