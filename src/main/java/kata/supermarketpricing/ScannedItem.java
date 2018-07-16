package kata.supermarketpricing;

import kata.supermarketpricing.quantity.Quantity;

/**
 * by Adil on 14/07/2018.
 */
public class ScannedItem {

    private String barCode;
    private Quantity quantity;

    public ScannedItem(String barCode, Quantity quantity) {
        this.barCode = barCode;
        this.quantity = quantity;
    }

    public String barCode() {
        return barCode;
    }

    public Quantity quantity() {
        return quantity;
    }
}
