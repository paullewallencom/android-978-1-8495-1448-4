/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.validation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;

/**
 *
 * @author Jason Morris
 */
public class MainActivity extends Activity implements OnClickListener {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        ((Button)findViewById(R.id.list)).setOnClickListener(this);
        ((Button)findViewById(R.id.date)).setOnClickListener(this);
    }

    public void onClick(final View view) {
        if(view.getId() == R.id.date) {
            final Calendar now = Calendar.getInstance();
            final ValidatingDatePickerDialog date = new ValidatingDatePickerDialog(
                    this,
                    null,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

            date.setValidationCallback(new ValidatingDatePickerDialog.ValidationCallback() {

                public boolean isValid(
                        final int year,
                        final int monthOfYear,
                        final int dayOfMonth) {

                    if(year < now.get(Calendar.YEAR)) {
                        return false;
                    } else if(monthOfYear < now.get(Calendar.MONTH)
                            && year == now.get(Calendar.YEAR)) {

                        return false;
                    } else if(dayOfMonth < now.get(Calendar.DAY_OF_MONTH)
                            && monthOfYear == now.get(Calendar.MONTH)
                            && year == now.get(Calendar.YEAR)) {

                        return false;
                    }

                    return true;
                }

            });

            date.show();
        } else if(view.getId() == R.id.list) {
            final Intent intent = new Intent(
                    this,
                    ListItemSelectionActivity.class);

            intent.setData(People.CONTENT_URI);
            intent.putExtra("line1", People.NAME);
            intent.putExtra("line2", People.NUMBER);

            startActivityForResult(intent, 101);
        }
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == RESULT_OK) {
            Toast.makeText(
                    this,
                    String.valueOf(data.getSerializableExtra("selection")),
                    Toast.LENGTH_LONG).show();
        }
    }

}
