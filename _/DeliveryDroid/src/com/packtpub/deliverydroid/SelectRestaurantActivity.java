package com.packtpub.deliverydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 *
 * @author Jason Morris
 */
public class SelectRestaurantActivity extends Activity implements OnItemClickListener {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        final ListView restaurants = (ListView)findViewById(R.id.restaurant);

        restaurants.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.menu_item,
                getResources().getStringArray(R.array.restaurants)));

        restaurants.setOnItemClickListener(this);
    }

    public void onItemClick(
            final AdapterView<?> parent,
            final View item,
            final int index,
            final long id) {

        switch(index) {
            case 0:
                startActivity(new Intent(
                        this,
                        TheBurgerPlaceActivity.class));
                break;
            case 1:
                startActivity(new Intent(
                        this,
                        MicksPizzaActivity.class));
                break;
            case 2:
                startActivity(new Intent(
                        this,
                        FourBucketsActivity.class));
        }
    }

}
