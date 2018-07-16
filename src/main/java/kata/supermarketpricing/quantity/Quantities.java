package kata.supermarketpricing.quantity;

import java.math.BigDecimal;

/**
 * by Adil on 14/07/2018.
 */
public class Quantities {

    public static Nothing nothing() {
        return new Nothing();
    }

    public static IntPiece pieces(int quantity) {
        return new IntPiece(quantity);
    }

    public static Kilogram kilos(double quantity) {
        return new Kilogram(quantity);
    }

    public static Gram grams(int quantity) {
        return new Gram(quantity);
    }

}
