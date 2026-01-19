import java.util.*;

public class CollectionFactoryMethods {
    public static void main(String[] args) {
        List<String> list = List.of("one", "two", "three");
        Set<String> set = Set.of("one", "two", "three");
        Map<String, String> map = Map.of("key", "val", "key2", "val2");
        try {
            list.remove(0);
            throw new RuntimeException("Shouldn't happen");
        } catch (UnsupportedOperationException e) {
            System.out.println("Tried to remove from immutable collection");
        }
    }
}