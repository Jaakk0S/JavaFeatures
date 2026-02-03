public class ScopedValues {

    private static final ScopedValue<String> NAME = ScopedValue.newInstance();
    private static final ScopedValue<Integer> AGE = ScopedValue.newInstance();

    static void main() {

        // Run a Runnable with given bindings
        ScopedValue
                .where(NAME, "Johnson")
                .where(AGE, 25)
                .run(() -> {
                    IO.println(NAME.get() + ": " + AGE.get());
                });

        // Return a value from CallableOp, with given bindings
        int val = ScopedValue
                .where(NAME, "Johnson")
                .where(AGE, 25)
                .call(AGE::get);
        IO.println(val);

    }
}
