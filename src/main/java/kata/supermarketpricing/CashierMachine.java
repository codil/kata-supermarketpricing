package kata.supermarketpricing;

/**
 * by Adil on 14/07/2018.
 */
public class CashierMachine {
    private ProductFinder productFinder;

    public CashierMachine(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    PartialCheckout newCheckout() { return new PartialCheckout(productFinder); }
}
