package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Scope("singleton")
    @Bean
    public Daytime getDaytimeBean() {
        var localDateTime = LocalDateTime.now();
        var isDay = localDateTime.getHour() >= 6 && localDateTime.getHour() <= 22;
        if (isDay) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
