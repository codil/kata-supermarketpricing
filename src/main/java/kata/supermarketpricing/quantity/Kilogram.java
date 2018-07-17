package kata.supermarketpricing.quantity;

import java.util.Objects;

/**
 * by Adil on 14/07/2018.
 */
class Kilogram implements Weight {

    private double value;

    Kilogram(double value) {
        this.value = value;
    }

    @Override
    public Kilogram plus(Quantity quantity) {
        checkType(quantity);
        return new Kilogram(this.value + ((Weight)quantity).toKilogram().value);
    }

    @Override
    public Kilogram[] dividedBy(Quantity quantity) {
        checkType(quantity);
        Kilogram himInKilo = ((Weight) quantity).toKilogram();
        double quotient = this.value / himInKilo.value;
        double remainder = this.value % himInKilo.value;
        return new Kilogram[]{ new Kilogram(quotient), new Kilogram(remainder) };
    }

    @Override
    public int[] partitionBy(Quantity quantity) {
        checkType(quantity);
        Kilogram himInKilo = ((Weight) quantity).toKilogram();
        int quotient = Double.valueOf(this.value / himInKilo.value).intValue();
        int remainder = Double.valueOf(this.value % himInKilo.value).intValue();
        return new int[] { quotient, remainder };
    }

    @Override
    public Kilogram times(int value) {
        return new Kilogram(this.value * value);
    }

    @Override
    public double ratio(Quantity quantity) {
        checkType(quantity);
        return ((Weight)quantity).toKilogram().value / this.value;
    }

    @Override
    public Kilogram minus(Quantity quantity) {
        checkType(quantity);
        return new Kilogram(this.value - ((Weight) quantity).toKilogram().value);
    }

    @Override
    public Kilogram toKilogram() {
        return new Kilogram(value);
    }

    @Override
    public Gram toGram() {
        return new Gram(Double.valueOf(value * 1000).intValue());
    }

    private void checkType(Quantity quantity) {
        if (!(quantity instanceof Weight)) throw new IllegalArgumentException("I can only handle weights");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weight)) return false;
        Kilogram kilogram = ((Weight) o).toKilogram();
        return Double.compare(kilogram.value, value) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "kg";
    }
}
