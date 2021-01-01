package com.packtpub.viewrecipe;

public class Ingredient {

    private final String name;

    private final double amount;

    private final String unit;

    public Ingredient(
            final String name,
            final double amount,
            final String unit) {

        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

}
