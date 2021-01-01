/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.packtpub.deliverydroid;

/**
 * <p>
 * </p><p>
 * Created on 09 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
class PizzaCatagory {

    final String name;

    final PizzaTopping[] toppings;

    public PizzaCatagory(
            final String name,
            final PizzaTopping... toppings) {
        
        this.name = name;
        this.toppings = toppings;
    }

}
