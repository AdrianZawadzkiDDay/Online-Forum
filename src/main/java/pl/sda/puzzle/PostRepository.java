package pl.sda.puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.puzzle.tables.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}