/*
 * Copyright Jason Morris 2008. All rights reserved.
 */
package com.packtpub.layouts;

import android.os.Bundle;

import android.app.ListActivity;

import android.content.Intent;

import android.view.View;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 *
 * @author Jason Morris
 */
public class LayoutSelectorActivity extends ListActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.layouts)));
    }

    @Override
    protected void onListItemClick(
            final ListView l,
            final View v,
            final int position,
            final long id) {

        super.onListItemClick(l, v, position, id);

        switch(position) {
            case 0:
                startActivity(new Intent(this, FrameLayoutActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, TableLayoutActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CircleLayoutActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, RelativeLayoutActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, SlidingDrawerActivity.class));
                break;
            default:
                Toast.makeText(
                        this,
                        "Example not yet implemented.",
                        Toast.LENGTH_SHORT).show();
        }
    }

}
