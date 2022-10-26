package site.matacoding.white.domain;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public Optional<Board> findById(Long id) {
        try {
            Optional<Board> boardOP = Optional.of(em
                    .createQuery("select b from Board b where b.id = :id",
                            Board.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return boardOP;
        } catch (Exception e) {
            return Optional.empty();
        }

    }
    // 한건이라서 getSingleResult를 사용한다/ 여러건이면 list
    // null을 받을수 있는 타입이 됨Optional은 박스인데 board를 넣고 셀렉도 넣고 박스 객체가 있으니까 박스는 null이 아닌것
    // 박스에서 꺼낼때 null이 있는지 체크하고 꺼냄

    public List<Board> findAll() {
        // JPQL 문법 //@Entity 붙은애들만 가능하다
        List<Board> boardList = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boardList;
    }

    public void deleteById(Long id) {
        em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
    // JPQL 문법
    // Board boardPS = em.createQuery("select b from Board b where b.id = :id",
    // Board.class)
    // .setParameter("id", id)
    // .getSingleResult();

    // Board boardPS = (Board) em
    // .createNativeQuery("select * from board b inner join user u on b.user_id =
    // u.id where b.id = :id",
    // Board.class)
    // .setParameter("id", id)
    // .getSingleResult();

    // Board boardPS = em
    // .createQuery("select b from Board b join fetch b.user u where b.id = :id",
    // Board.class)
    // .setParameter("id", id)
    // .getSingleResult();
}
// 디비에 쿼리를 넘겨주는것