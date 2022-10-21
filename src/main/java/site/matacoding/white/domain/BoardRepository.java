package site.matacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IOC컨테이너에 등록이 된다.
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);// insert쿼리가 자동으로 돌아간다 -> insert
    }

    public Board findById(Long id) {
        // JPQL 문법
        Board boardPS = em.createQuery("select b from Board b where b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();// 엔티티조회 쿼리
        return boardPS;
    }// 한건이라서 getSingleResult를 사용한다/ 여러건이면 list

    public List<Board> findAll() {
        // JPQL 문법
        List<Board> boardList = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boardList;
    }

    public void deleteById(Long id) {
        em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
// 디비에 쿼리를 넘겨주는것