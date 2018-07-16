package kata.supermarketpricing.quantity;

import java.util.Objects;

/**
 * by Adil on 14/07/2018.
 */
public class Gram implements Weight {

    private int value;

    Gram(int value) {
        this.value = value;
    }

    @Override
    public Gram plus(Quantity quantity) {
        checkType(quantity);
        return new Gram(((Weight)quantity).toGram().value + value);
    }

    @Override
    public Gram[] dividedBy(Quantity quantity) {
        checkType(quantity);
        Gram himInGram = ((Weight) quantity).toGram();
        int quotient = this.value / himInGram.value;
        int remainder = this.value % himInGram.value;
        return new Gram[]{ new Gram(quotient), new Gram(remainder) };
    }

    @Override
    public int[] partitionBy(Quantity quantity) {
        checkType(quantity);
        Gram himInGram = ((Weight) quantity).toGram();
        int quotient = this.value / himInGram.value;
        int remainder = this.value % himInGram.value;
        return new int[] { quotient, remainder };
    }

    @Override
    public Gram times(int value) {
        return new Gram(this.value * value);
    }

    @Override
    public double ratio(Quantity quantity) {
        checkType(quantity);
        return ((Weight)quantity).toGram().value / this.value;
    }

    @Override
    public Gram minus(Quantity quantity) {
        checkType(quantity);
        return new Gram(this.value - ((Weight) quantity).toGram().value);
    }

    @Override
    public Kilogram toKilogram() {
        return new Kilogram((double)value / 1000);
    }

    @Override
    public Gram toGram() {
        return new Gram(value);
    }

    private void checkType(Quantity quantity) {
        if (!(quantity instanceof Weight)) throw new IllegalArgumentException("I can only handle weights");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weight)) return false;
        Gram gram = ((Weight) o).toGram();
        return value == gram.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "g";
    }
}
