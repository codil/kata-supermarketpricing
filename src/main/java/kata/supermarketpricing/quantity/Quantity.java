package kata.supermarketpricing.quantity;

/**
 * by Adil on 14/07/2018.
 */
public interface Quantity {
    Quantity plus(Quantity value);
    Quantity[] dividedBy(Quantity quantity);
    int[] partitionBy(Quantity quantity);
    Quantity times(int value);
    double ratio(Quantity quantity);
    Quantity minus(Quantity quantity);
}
