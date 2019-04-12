package kr.hs.dgsw.web_dgswsns.Repository;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
