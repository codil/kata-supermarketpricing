package kata.supermarketpricing.quantity;

import org.junit.Test;

import static kata.supermarketpricing.quantity.Quantities.pieces;
import static org.junit.Assert.assertEquals;

/**
 * by Adil on 15/07/2018.
 */
public class IntPieceTests {

    @Test
    public void shouldMakeAddition() {
        assertEquals(pieces(3), pieces(1).plus(pieces(2)));
    }

    @Test
    public void shouldMakeDivision() {
        assertEquals(pieces(2), pieces(4).dividedBy(pieces(2))[0]);
        assertEquals(pieces(3), pieces(8).dividedBy(pieces(5))[1]);
    }

    @Test
    public void shouldMakePartitions() {
        assertEquals(3, pieces(6).partitionBy(pieces(2))[0]);
        assertEquals(2, pieces(6).partitionBy(pieces(4))[1]);
    }

    @Test
    public void shouldRepeatNTime() {
        assertEquals(pieces(6), pieces(2).times(3));
    }

    @Test
    public void shouldMakeSubstation() {
        assertEquals(pieces(4), pieces(8).minus(pieces(4)));
    }

}
