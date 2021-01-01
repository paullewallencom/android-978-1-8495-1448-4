/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.guessnumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * @author Jason Morris
 */
public class GuessActivity extends Activity implements OnClickListener {

    private int number;

    private static int random() {
        return (int)(Math.random() * 9) + 1;
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Number", number);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            number = savedInstanceState.getInt("Number", random());
        } else {
            number = random();
        }

        setContentView(R.layout.main);

        final Button button = (Button)findViewById(R.id.guess);
        button.setOnClickListener(this);
    }

    public void onClick(final View clicked) {
        final EditText input = (EditText)findViewById(R.id.number);
        final int value = Integer.parseInt(input.getText().toString());

        if(value < number) {
            Toast.makeText(this, "To low", Toast.LENGTH_SHORT).show();
        } else if(value > number) {
            Toast.makeText(this, "To high", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(
                    this,
                    "You got it! Try guess another one!",
                    Toast.LENGTH_SHORT).show();

            number = random();
        }
    }

}
