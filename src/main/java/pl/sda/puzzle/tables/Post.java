package pl.sda.puzzle.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User author;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post(){}

    public Post(User author, @NotEmpty String title, @NotEmpty String content, List<Comment> comments) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
