package exercise;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

// BEGIN
@Value
@AllArgsConstructor
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
