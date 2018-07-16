package kata.supermarketpricing.quantity;

import org.junit.Test;

import static kata.supermarketpricing.quantity.Quantities.grams;
import static kata.supermarketpricing.quantity.Quantities.kilos;
import static org.junit.Assert.*;

/**
 * by Adil on 15/07/2018.
 */
public class GramTests {

    @Test
    public void shouldMakeAddition() {
        assertEquals(grams(500), grams(300).plus(grams(200)));
        assertEquals(grams(300), grams(100).plus(kilos(0.2)));
    }

    @Test
    public void shouldMakeDivision() {
        assertEquals(grams(200), grams(600).dividedBy(grams(3))[0]);
        assertEquals(grams(1), grams(400).dividedBy(grams(3))[1]);
    }

    @Test
    public void shouldMakePartitions() {
        assertEquals(3, grams(600).partitionBy(grams(200))[0]);
        assertEquals(200, grams(600).partitionBy(grams(400))[1]);
    }

    @Test
    public void shouldRepeatNTime() {
        assertEquals(grams(600), grams(200).times(3));
    }

    @Test
    public void shouldMakeSubstation() {
        assertEquals(grams(400), grams(600).minus(grams(200)));
        assertEquals(grams(150), grams(200).minus(kilos(0.05)));
    }

    @Test
    public void shouldConvertToKilogram() {
        assertEquals(grams(1200), kilos(1.2).toGram());
    }

}
