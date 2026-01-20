import java.util.stream.Stream;

public class StreamAPIEnhancements {
    public static void main(String[] args) {

        System.out.println("Keep printing elements until encountering 'four'");
        Stream.of("one", "two", "three", "four", "five").takeWhile(s -> !s.equals("four")).forEach(System.out::println);

        System.out.println("Keep ignoring elements until encountering 'four'");
        Stream.of("one", "two", "three", "four", "five").dropWhile(s -> !s.equals("four")).forEach(System.out::println);

        System.out.println("Starting from 1, iterate n + 16 while n < 100");
        Stream.iterate(1, n -> n < 100, n -> n + 16).forEach(System.out::println);

        String nullString = null;
        System.out.println("Print length of stream created with of() and has a null element");
        System.out.println(Stream.of(nullString).count());
        System.out.println("Print length of stream created with ofNullable and has a null element");
        System.out.println(Stream.ofNullable(nullString).count());
    }
}