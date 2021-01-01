/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.deliverydroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.GridView;

/**
 * <p>
 * </p><p>
 * Created on 10 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
public class FourBucketsActivity extends Activity {

    @Override
    protected void onCreate(final Bundle istate) {
        super.onCreate(istate);

        final LayoutInflater inflater = getLayoutInflater();
        
        final GridView view = (GridView)inflater.inflate(
                R.layout.four_buckets,
                null);

        view.setAdapter(new FruitAdapter(
                new FruitItem("Apple", R.drawable.apple),
                new FruitItem("Banana", R.drawable.banana),
                new FruitItem("Black Berries", R.drawable.blackberry),
                new FruitItem("Cherries", R.drawable.cherries),
                new FruitItem("Coconut", R.drawable.coconut),
                new FruitItem("Grapes", R.drawable.grapes),
                new FruitItem("Kiwi", R.drawable.kiwi),
                new FruitItem("Lemon", R.drawable.lemon),
                new FruitItem("Lime", R.drawable.lime),
                new FruitItem("Orange", R.drawable.orange),
                new FruitItem("Peach", R.drawable.peach),
                new FruitItem("Strawberries", R.drawable.strawberry),
                new FruitItem("Watermelon", R.drawable.watermelon)));

        setContentView(view);
    }

}
