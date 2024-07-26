package exercise;

import lombok.AllArgsConstructor;
import lombok.Value;

// BEGIN
@Value
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
