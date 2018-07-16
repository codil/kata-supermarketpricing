package kata.supermarketpricing.quantity;

import org.junit.Test;

import static kata.supermarketpricing.quantity.Quantities.*;
import static org.junit.Assert.*;

/**
 * by Adil on 15/07/2018.
 */
public class KilogramTest {

    @Test
    public void shouldMakeAddition() {
        assertEquals(kilos(3), kilos(1).plus(kilos(2)));
        assertEquals(kilos(1.5), kilos(1).plus(grams(500)));
    }

    @Test
    public void shouldMakeDivision() {
        assertEquals(kilos(2), kilos(4).dividedBy(kilos(2))[0]);
        assertEquals(kilos(3), kilos(8).dividedBy(kilos(5))[1]);
    }

    @Test
    public void shouldMakePartitions() {
        assertEquals(3, kilos(6).partitionBy(kilos(2))[0]);
        assertEquals(2, kilos(6).partitionBy(kilos(4))[1]);
    }

    @Test
    public void shouldRepeatNTime() {
        assertEquals(kilos(6), kilos(2).times(3));
    }

    @Test
    public void shouldMakeSubstation() {
        assertEquals(kilos(4), kilos(8).minus(kilos(4)));
        assertEquals(kilos(1.5), kilos(2).minus(kilos(0.5)));
    }

    @Test
    public void shouldConvertToGram() {
        assertEquals(grams(500), kilos(0.5).toGram());
    }




}
