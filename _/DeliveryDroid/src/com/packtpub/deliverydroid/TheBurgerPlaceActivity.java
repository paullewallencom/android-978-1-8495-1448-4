package com.packtpub.deliverydroid;

import android.app.ListActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;

/**
 * <p>
 * </p><p>
 * Created on 03 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
public class TheBurgerPlaceActivity extends ListActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new BurgerAdapter(
                new Burger("Plain old Burger"),
                new Burger("Cheese Burger"),
                new Burger("Chicken Burger"),
                new Burger("Breakfast Burger"),
                new Burger("Hawaiian Burger"),
                new Burger("Fish Burger"),
                new Burger("Vegatarian Burger"),
                new Burger("Lamb Burger"),
                new Burger("Rare Tuna Steak Burger")));
    }

    @Override
    protected void onListItemClick(
            final ListView parent,
            final View item,
            final int index,
            final long id) {

        super.onListItemClick(parent, item, index, id);

        final BurgerAdapter burgers = (BurgerAdapter)parent.getAdapter();
        final Burger burger = (Burger)burgers.getItem(index);
        burger.count++;
        burgers.notifyDataSetInvalidated();
    }

}
