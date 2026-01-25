import java.util.stream.*;

public class TeeingCollector {
    public static void main(String[] args) {
        System.out.println((double) Stream.of(1, 2, 3, 4, 5).collect(Collectors.teeing(
            Collectors.summingDouble(i -> i),   // collector 1: returns the sum of elements = 15
            Collectors.counting(),                     // collector 2: returns the number of elements = 5
            (sum, count) -> sum / count   // merge function: returns the sum divided by the number of elements
        )));
    }
}
