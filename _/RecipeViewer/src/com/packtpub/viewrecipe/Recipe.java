package com.packtpub.viewrecipe;

import java.text.DecimalFormat;

public class Recipe {

    private final String name;

    private final Ingredient[] ingredients;

    private final String[] instructions;

    public Recipe(
            final String name,
            final Ingredient[] ingredients,
            final String[] instructions) {

        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public String getName() {
        return name;
    }

    public String toHtml() {
        final DecimalFormat format = new DecimalFormat("#0.##");
        final StringBuilder s = new StringBuilder();

        s.append("<html>");
        s.append("<body>");

        s.append("<h1>").append(getName()).append("</h1>");
        s.append("<h2>You will need:</h2>");

        s.append("<ul class=\"ingredients\">");
        for(final Ingredient i : getIngredients()) {
            s.append("<li>");
            s.append(format.format(i.getAmount()));
            s.append(i.getUnit());
            s.append(" - ").append(i.getName());
            s.append("</li>");
        }
        s.append("</ul>");

        s.append("<h2>Instructions:</h2>");
        s.append("<ul class=\"instructions\">");
        for(final String i : getInstructions()) {
            s.append("<li>").append(i).append("</li>");
        }
        s.append("</ul>");

        s.append("</body>");
        s.append("</html>");

        return s.toString();
    }

}
