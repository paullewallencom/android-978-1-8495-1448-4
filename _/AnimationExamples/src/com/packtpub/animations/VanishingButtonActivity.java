package com.packtpub.animations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class VanishingButtonActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vanish);

        final Button button = (Button)findViewById(R.id.vanish);
        button.setOnClickListener(this);
    }

    public void onClick(final View view) {
        final Animation vanish = AnimationUtils.loadAnimation(
                this, R.anim.vanish);
        
        findViewById(R.id.vanish).startAnimation(vanish);
    }

}
