package pl.sda.puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.puzzle.tables.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
