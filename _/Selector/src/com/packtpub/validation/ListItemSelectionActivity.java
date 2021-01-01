package com.packtpub.validation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import java.io.Serializable;

/**
 * <p>
 * </p><p>
 * Created on 25 Sep 2010
 * </p>
 *
 * @author Jason Morris
 */
public class ListItemSelectionActivity extends Activity
        implements TextWatcher, OnItemClickListener {

    private ListAdapter adapter;

    private Filter filter;

    private ListAdapter createArrayAdapter(final Intent intent) {
        final Object[] data = (Object[])intent.getSerializableExtra("data");

        if(data != null && data.length > 0) {
            return new ArrayAdapter<Object>(
                    this,
                    android.R.layout.simple_list_item_1,
                    data);
        } else {
            throw new IllegalArgumentException(
                    "no list data specified in Intent: "
                    + intent);
        }
    }

    private String getColumnName(
            final Intent intent,
            final String primary,
            final String secondary,
            final String def) {

        String col = intent.getStringExtra(primary);

        if(col == null) {
            col = intent.getStringExtra(secondary);
        }

        if(col == null) {
            col = def;
        }

        return col;
    }

    private ListAdapter createCursorAdapter(final Intent intent) {
        final String line1 = getColumnName(intent, "name", "line1", "name");
        final String line2 = getColumnName(intent, "description", "line2", null);

        final int listItemResource;
        final String[] columns;
        final String[] displayColumns;
        final int[] textIds;

        if(line2 != null) {
            listItemResource = android.R.layout.two_line_list_item;
            columns = new String[]{"_id", line1, line2};
            displayColumns = new String[]{line1, line2};
            textIds = new int[]{android.R.id.text1, android.R.id.text2};
        } else {
            listItemResource = android.R.layout.simple_list_item_1;
            columns = new String[]{"_id", line1};
            displayColumns = new String[]{line1};
            textIds = null;
        }

        final Cursor cursor = managedQuery(
                intent.getData(),
                columns,
                null,
                null,
                line1);

        final CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                listItemResource,
                cursor,
                displayColumns,
                textIds);

        cursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {

            public Cursor runQuery(final CharSequence constraint) {
                return managedQuery(
                        intent.getData(),
                        columns,
                        line1 + " LIKE ?",
                        new String[] {constraint.toString() + '%'},
                        line1);
            }

        });

        return cursorAdapter;
    }

    protected ListAdapter createListAdapter() {
        final Intent intent = getIntent();
        
        if(intent.getData() != null) {
            return createCursorAdapter(intent);
        } else {
            return createArrayAdapter(intent);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_selection);

        final ListView list = (ListView)findViewById(R.id.list);
        final EditText input = (EditText)findViewById(R.id.input);

        adapter = createListAdapter();
        filter = ((Filterable)adapter).getFilter();

        list.setAdapter(adapter);
        input.addTextChangedListener(this);
        list.setOnItemClickListener(this);
    }

    public void beforeTextChanged(
            final CharSequence s,
            final int start,
            final int count,
            final int after) {
    }

    public void onTextChanged(
            final CharSequence s,
            final int start,
            final int count,
            final int after) {
    }

    public void afterTextChanged(final Editable s) {
        filter.filter(s);
    }

    public void onItemClick(
            final AdapterView<?> parent,
            final View clicked,
            final int position,
            final long id) {

        final Intent data = new Intent();

        if(adapter instanceof CursorAdapter) {
            data.putExtra("selection", id);
        } else {
            data.putExtra(
                    "selection",
                    (Serializable)parent.getItemAtPosition(position));
        }

        setResult(RESULT_OK, data);
        finish();
    }

}
