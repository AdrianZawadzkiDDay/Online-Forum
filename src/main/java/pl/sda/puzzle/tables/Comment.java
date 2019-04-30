package pl.sda.puzzle.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 300)
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    public Comment(){

    }

    public Comment(String content, User user, Post post){
        this.content = content;
        this.author = user;
        this.post = post;
    }

    public Comment(@Size(max = 300) String content, User author) {
        this.content = content;
        this.author = author;
    }



    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    @JsonIgnore
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
