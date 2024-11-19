package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        return list.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .limit(n)
                .toList();
    }
}
// END
