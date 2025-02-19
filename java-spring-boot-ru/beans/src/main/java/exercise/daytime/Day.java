package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    private void info() {
        System.out.println("Bean \"Day\" rdy");
    }
    // END
}
