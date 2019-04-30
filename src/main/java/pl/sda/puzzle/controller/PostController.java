package pl.sda.puzzle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.puzzle.PostRepository;
import pl.sda.puzzle.UserDetailsServiceImpl;
import pl.sda.puzzle.dto.CommentDto;
import pl.sda.puzzle.tables.Comment;
import pl.sda.puzzle.tables.Post;
import pl.sda.puzzle.tables.User;

import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepository;
    private UserDetailsServiceImpl userDetailsService;

    public PostController(PostRepository postRepository, UserDetailsServiceImpl userDetailsService) {
        this.postRepository = postRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<Post> get(){return postRepository.findAll();}

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Post post(@RequestBody Post post){
//        return postRepository.save(post);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post post(Principal principal, @RequestBody Post post){
        //(User author, @NotEmpty String title, @NotEmpty String content, Set<Comment> comments)
        User author = (User) userDetailsService.loadUserByUsername(principal.getName());
        post.setAuthor(author);

        return postRepository.save(post);
    }

    @GetMapping("{id}")
    public Post findById(@PathVariable long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono postu o takim id"));
    }


}
