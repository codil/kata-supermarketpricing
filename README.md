This is a solution for the supermarket pricing kata, based on Java 8.

Each product has a pricing scheme (a kind of strategy pattern).

Concepts like immutable objects, non-anemic domain model, lambdas and streams processing are used.

I tried to simulate the checkout process of a cashier machine where bar codes are scanned, prices fetched, and invoices produced.

The pipeline is : scanned item --> product --> invoice items

An example of that workflow is tested in `CashierMachineTests` test class.

Key classes are javadoc-ed.