package site.matacoding.white.domain;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommentRepository {
    private final EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);// insert쿼리가 자동으로 돌아간다 -> insert
        return comment;
    }

    public void deleteById(Long id) {
        em.createQuery("delete from comment c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Optional<Comment> findById(Long id) {
        try {
            Optional<Comment> commentOP = Optional.of(em
                    .createQuery("select c from Comment c where c.id = :id",
                            Comment.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return commentOP;
        } catch (Exception e) {
            return Optional.empty();
        }

    }

}
