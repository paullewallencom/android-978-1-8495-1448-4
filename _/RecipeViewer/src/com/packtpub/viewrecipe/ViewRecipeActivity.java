package com.packtpub.viewrecipe;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import java.text.DecimalFormat;

public class ViewRecipeActivity extends Activity {

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        final WebView view = new WebView(this);
        view.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));

        final Recipe recipe = new Recipe(
                "Microwave Fudge",
                new Ingredient[]{
                    new Ingredient("Condensed Milk", 385, "grams"),
                    new Ingredient("Sugar", 500, "grams"),
                    new Ingredient("Margarine", 125, "grams"),
                    new Ingredient("Vanilla Essence", 5, "ml")
                },
                new String[]{
                    "Combine the condensed milk, sugar and margarine "
                    + "in a large microwave-proof bowl",
                    "Microwave for 2 minutes on full power",
                    "Remove from microwave and stir well",
                    "Microwave for additional 5 minutes on full power",
                    "Add the Vanilla essence and stir",
                    "Pour into a greased dish",
                    "Allow to cool",
                    "Cut into small squares"
                });

        view.loadData(recipe.toHtml(), "text/html", "UTF-8");
        setContentView(view);
    }

}
