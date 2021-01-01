package com.packtpub.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.Arrays;
import java.util.Collections;

public class TableLayoutActivity extends Activity {

    private static final int[] CARD_RESOURCES = new int[]{
        R.drawable.apple,
        R.drawable.banana,
        R.drawable.blackberry,
        R.drawable.cherries,
        R.drawable.coconut,
        R.drawable.grapes,
        R.drawable.kiwi,
        R.drawable.lemon,
        R.drawable.line,
        R.drawable.orange,
        R.drawable.peach,
        R.drawable.strawberry,
        R.drawable.watermelon
    };

    private final Handler handler = new Handler();

    private MemoryCard[] cards;

    private MemoryCard visible = null;

    private boolean touchEnabled = true;

    private class MemoryCard implements OnClickListener {

        private final ImageButton button;

        private final int faceImage;

        private boolean faceVisible = false;

        MemoryCard(final int faceImage) {
            this.faceImage = faceImage;
            this.button = new ImageButton(TableLayoutActivity.this);
            this.button.setLayoutParams(new TableRow.LayoutParams(64, 64));
            this.button.setScaleType(ScaleType.FIT_XY);
            this.button.setImageResource(R.drawable.line);
            this.button.setOnClickListener(this);
        }

        void setFaceVisible(final boolean faceVisible) {
            this.faceVisible = faceVisible;
            button.setImageResource(faceVisible
                    ? faceImage
                    : R.drawable.line);
        }

        public void onClick(final View view) {
            if(!faceVisible && touchEnabled) {
                onMemoryCardUncovered(this);
            }
        }

    }

    private MemoryCard[] createMemoryCells(final int count) {
        final MemoryCard[] array = new MemoryCard[count];

        for(int i = 0; i < count; i++) {
            array[i] = new MemoryCard(CARD_RESOURCES[i / 2]);
        }

        return array;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TableLayout table = new TableLayout(this);

        final int size = 4;
        cards = createMemoryCells(size * size);
        Collections.shuffle(Arrays.asList(cards));

        for(int y = 0; y < size; y++) {
            final TableRow row = new TableRow(this);
            for(int x = 0; x < size; x++) {
                row.addView(cards[(y * size) + x].button);
            }
            table.addView(row);
        }

        setContentView(table);
    }

    private void onMemoryCardUncovered(final MemoryCard cell) {
        if(visible == null) {
            visible = cell;
            visible.setFaceVisible(true);
        } else if(visible.faceImage == cell.faceImage) {
            cell.setFaceVisible(true);
            cell.button.setEnabled(false);
            visible.button.setEnabled(false);
            visible = null;
        } else {
            cell.setFaceVisible(true);
            touchEnabled = false;

            handler.postDelayed(new Runnable() {

                public void run() {
                    cell.setFaceVisible(false);
                    visible.setFaceVisible(false);
                    visible = null;
                    touchEnabled = true;
                }

            }, 1000);
        }
    }

}
