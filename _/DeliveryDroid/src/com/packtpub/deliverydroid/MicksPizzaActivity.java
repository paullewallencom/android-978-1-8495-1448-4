/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.deliverydroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * <p>
 * </p><p>
 * Created on 09 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
public class MicksPizzaActivity extends Activity implements ExpandableListView.OnChildClickListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.micks_pizza);

        final PizzaCatagory[] catagories = new PizzaCatagory[]{
            new PizzaCatagory("Fruit",
            new PizzaTopping("Pineapple"),
            new PizzaTopping("Avocado"),
            new PizzaTopping("Apple")),
            new PizzaCatagory("Meat",
            new PizzaTopping("Ham"),
            new PizzaTopping("Bacon"),
            new PizzaTopping("Beef Mince"),
            new PizzaTopping("Ribs"),
            new PizzaTopping("Lamb"),
            new PizzaTopping("Pepperoni"),
            new PizzaTopping("Chorizo")),
            new PizzaCatagory("Sauces",
            new PizzaTopping("Tomato & Herb"),
            new PizzaTopping("Sweet & Sour"),
            new PizzaTopping("Fruit Chutney"),
            new PizzaTopping("Barbecue"),
            new PizzaTopping("Pesto"))
        };

        final ExpandableListView list =
                (ExpandableListView)findViewById(R.id.pizzas);

        list.setAdapter(new PizzaToppingsAdapter(catagories));
        list.setOnChildClickListener(this);
    }

    public boolean onChildClick(
            final ExpandableListView list,
            final View item,
            final int group,
            final int child,
            final long id) {

        final PizzaToppingsAdapter adapter =
                (PizzaToppingsAdapter)list.getExpandableListAdapter();

        Log.i("ToppingAdapter", "" + adapter);

        final PizzaTopping topping =
                (PizzaTopping)adapter.getChild(group, child);
        Log.i("Topping", "" + topping);

        topping.nextState();

        adapter.notifyDataSetInvalidated();

        return true;
    }

}
