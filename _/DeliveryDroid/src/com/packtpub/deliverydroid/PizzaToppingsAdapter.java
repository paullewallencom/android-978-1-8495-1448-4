/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.deliverydroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * <p>
 * </p><p>
 * Created on 09 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
class PizzaToppingsAdapter extends BaseExpandableListAdapter {

    private final PizzaCatagory[] catagories;

    PizzaToppingsAdapter(final PizzaCatagory... catagories) {
        this.catagories = catagories;
    }

    private TextView getGroupView(final View reuse, final ViewGroup parent) {
        if(reuse instanceof TextView) {
            return (TextView)reuse;
        } else {
            final Context context = parent.getContext();
            final LayoutInflater inflater = LayoutInflater.from(context);
            return (TextView)inflater.inflate(
                    android.R.layout.simple_expandable_list_item_1,
                    null);
        }
    }

    private ViewGroup getChildViewGroup(
            final View reuse,
            final ViewGroup parent) {

        if(reuse instanceof ViewGroup) {
            return (ViewGroup)reuse;
        } else {
            final Context context = parent.getContext();
            final LayoutInflater inflater = LayoutInflater.from(context);
            return (ViewGroup)inflater.inflate(
                    R.layout.pizza_item,
                    null);
        }
    }

    public int getGroupCount() {
        return catagories.length;
    }

    public int getChildrenCount(final int group) {
        return catagories[group].toppings.length;
    }

    public Object getGroup(final int group) {
        return catagories[group];
    }

    public Object getChild(final int group, final int child) {
        return catagories[group].toppings[child];
    }

    public long getGroupId(final int group) {
        return group * 1000l;
    }

    public long getChildId(final int group, final int child) {
        return getGroupId(group) + child;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getGroupView(
            final int group,
            final boolean expanded,
            final View reuse,
            final ViewGroup parent) {

        final TextView view = getGroupView(reuse, parent);
        view.setText(catagories[group].name);
        return view;
    }

    public View getChildView(
            final int group,
            final int child,
            final boolean lastChild,
            final View reuse,
            final ViewGroup parent) {

        final ViewGroup view = getChildViewGroup(reuse, parent);
        final PizzaTopping topping = catagories[group].toppings[child];

        ((TextView)view.findViewById(R.id.text)).setText(topping.name);

        switch(topping.state) {
            case PizzaTopping.STATE_OFF:
                ((TextView)view.findViewById(R.id.status)).setText("");
                break;
            case PizzaTopping.STATE_ON:
                ((TextView)view.findViewById(R.id.status)).setText("On");
                break;
            case PizzaTopping.STATE_EXTRA:
                ((TextView)view.findViewById(R.id.status)).setText("Extra");
                break;
        }

        return view;
    }

    public boolean isChildSelectable(final int group, final int child) {
        return true;
    }
}
