package exercise.controller;

import exercise.daytime.Daytime;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@AllArgsConstructor
@RestController
public class WelcomeController {
    private Daytime daytime;


    @GetMapping(path = "/welcome")
    public String welcome() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }
}
// END
