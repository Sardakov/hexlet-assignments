package exercise.dto.users;

import exercise.model.User;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// BEGIN
@AllArgsConstructor
@Getter
@ToString
public class UsersPage {
    private List<User> users;
}
// END
