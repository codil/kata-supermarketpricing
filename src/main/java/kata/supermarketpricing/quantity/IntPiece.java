package kata.supermarketpricing.quantity;

import java.util.Objects;

/**
 * by Adil on 14/07/2018.
 */
public class IntPiece implements Quantity, Piece {

    private int value;

    IntPiece(int value) {
        this.value = value;
    }

    @Override
    public IntPiece plus(Quantity quantity) {
        checkType(quantity);
        return new IntPiece(this.value + ((IntPiece) quantity).value);
    }

    @Override
    public Piece[] dividedBy(Quantity quantity) {
        checkType(quantity);
        IntPiece piece = (IntPiece) quantity;
        int quotient = this.value / piece.value;
        int remainder = this.value % piece.value;
        return new IntPiece[]{ new IntPiece(quotient), new IntPiece(remainder) };
    }

    @Override
    public int[] partitionBy(Quantity quantity) {
        checkType(quantity);
        IntPiece piece = (IntPiece) quantity;
        int quotient = this.value / piece.value;
        int remainder = this.value % piece.value;
        return new int[] { quotient, remainder };
    }

    @Override
    public IntPiece times(int value) {
        return new IntPiece(this.value * value);
    }

    @Override
    public double ratio(Quantity quantity) {
        checkType(quantity);
        return ((IntPiece)quantity).value / this.value;
    }

    @Override
    public IntPiece minus(Quantity quantity) {
        checkType(quantity);
        return new IntPiece(this.value - ((IntPiece) quantity).value);
    }

    private void checkType(Quantity quantity) {
        if (!(quantity instanceof Piece)) throw new IllegalArgumentException("I can only handle pieces");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPiece intPiece = (IntPiece) o;
        return value == intPiece.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "(x" + value + ")";
    }
}
