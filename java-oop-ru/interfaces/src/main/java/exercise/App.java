package exercise;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> data, int count) {
        return data.stream()
                .sorted(Comparator.comparing(Home::getArea))
                .limit(count)
                .map(Object::toString)
                .toList();
    }
}
