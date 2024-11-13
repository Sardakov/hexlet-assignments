package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping()
    public List<PostDTO> index() {
        var post = postRepository.findAll();
        var result = post.stream()
                .map(this::toDTO)
                .toList();
        return result;

    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var result = toDTO(post);

        return result;
    }

    private PostDTO toDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());

        List<Comment> comments = commentRepository.findByPostId(post.getId());

        List<CommentDTO> commentDTOS = comments.stream()
                .map( comment -> {
                    var commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    return commentDTO;
                })
                .collect(Collectors.toList());
        dto.setComments(commentDTOS);
        return dto;
    }
}
// END
