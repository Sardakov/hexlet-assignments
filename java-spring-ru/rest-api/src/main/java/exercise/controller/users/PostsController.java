package exercise.controller.users;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getUserId() == Integer.parseInt(id))
                .toList();
        return ResponseEntity.of(Optional.of(post));
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create (@PathVariable String id, @RequestBody Post post) {
        post.setUserId(Integer.parseInt(id));
        posts.add(post);
        URI location = URI.create("/users/" + post.getUserId() + "/posts");
        return ResponseEntity.created(location).body(post);
    }
}
// END
