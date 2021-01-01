package com.packtpub.deliverydroid;

import android.content.Context;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.widget.TextView;
import android.widget.BaseAdapter;

class BurgerAdapter extends BaseAdapter {

    private final Burger[] burgers;

    BurgerAdapter(final Burger... burgers) {
        this.burgers = burgers;
    }

    private ViewGroup getViewGroup(final View reuse, final ViewGroup parent) {
        if(reuse instanceof ViewGroup) {
            return (ViewGroup)reuse;
        } else {
            final Context context = parent.getContext();
            final LayoutInflater inflater = LayoutInflater.from(context);

            final ViewGroup item = (ViewGroup)inflater.inflate(
                    R.layout.burger_item,
                    null);

            return item;
        }
    }

    public int getCount() {
        return burgers.length;
    }

    public Object getItem(final int index) {
        return burgers[index];
    }

    public long getItemId(final int index) {
        return index;
    }

    public View getView(
            final int index,
            final View reuse,
            final ViewGroup parent) {

        final ViewGroup item = getViewGroup(reuse, parent);
        final TextView counter = (TextView)item.findViewById(R.id.counter);
        final TextView label = (TextView)item.findViewById(R.id.text);
        final Burger burger = burgers[index];

        counter.setVisibility(
                burger.count == 0
                ? View.INVISIBLE
                : View.VISIBLE);

        counter.setText(Integer.toString(burger.count));
        label.setText(burger.name);

        return item;
    }

}
