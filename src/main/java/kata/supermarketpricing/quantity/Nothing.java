package kata.supermarketpricing.quantity;

/**
 * by Adil on 15/07/2018.
 */
public class Nothing implements Piece, Weight {

    Nothing() { }

    @Override
    public Quantity plus(Quantity value) {
        return value;
    }

    @Override
    public Nothing[] dividedBy(Quantity quantity) {
        return new Nothing[]  { new Nothing(), new Nothing() };
    }

    @Override
    public int[] partitionBy(Quantity quantity) {
        return new int[] { 1, 0 };
    }

    @Override
    public Nothing times(int value) {
        return new Nothing();
    }

    @Override
    public double ratio(Quantity quantity) {
        return 1;
    }

    @Override
    public Quantity minus(Quantity quantity) {
        return new Nothing();
    }

    @Override
    public Kilogram toKilogram() {
        return new Kilogram(0);
    }

    @Override
    public Gram toGram() {
        return new Gram(0);
    }
}
