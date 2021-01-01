/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.roboticreview;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.widget.ImageView;
import android.widget.BaseAdapter;

/**
 * <p>
 * </p><p>
 * Created on 21 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
public class GalleryAdapter extends BaseAdapter {

    private final int[] thumbnails = new int[]{
        R.drawable.curry_view_thn,
        R.drawable.jai_thn
    };

    private final int[] images = new int[]{
        R.drawable.curry_view,
        R.drawable.jai
    };

    public int getCount() {
        return thumbnails.length;
    }

    public Object getItem(final int index) {
        return Integer.valueOf(images[index]);
    }

    public long getItemId(final int index) {
        return images[index];
    }

    public View getView(
            final int index,
            final View reuse,
            final ViewGroup parent) {

        final ImageView view = (reuse instanceof ImageView)
                ? (ImageView)reuse
                : (ImageView)LayoutInflater.from(parent.getContext()).inflate(
                R.layout.gallery_thn,
                null);

        view.setImageResource(images[index]);

        return view;
    }

}
