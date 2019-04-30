package pl.sda.puzzle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.puzzle.CommentRepository;
import pl.sda.puzzle.PostRepository;
import pl.sda.puzzle.UserDetailsServiceImpl;
import pl.sda.puzzle.dto.CommentDto;
import pl.sda.puzzle.tables.Comment;
import pl.sda.puzzle.tables.Post;
import pl.sda.puzzle.tables.User;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/comments")
public class AddCommentController {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserDetailsServiceImpl userDetailsService;

    public AddCommentController(CommentRepository commentRepository, PostRepository postRepository, UserDetailsServiceImpl userDetailsService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public Collection<Comment> get(){return commentRepository.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment post(@RequestBody CommentDto commentDto, Principal principal){
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono postu o takim id(z AddComment)"));
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());

        Comment comment = new Comment(commentDto.getContent(), user, post);

        return commentRepository.save(comment);
    }
}
