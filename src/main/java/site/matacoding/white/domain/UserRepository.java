package site.matacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository // IoC 등록
public class UserRepository {

    // DI
    private final EntityManager em;

    // IOC 컨테이너는 싱글톤 디자인 패턴을 사용한다, 즉 한개만 사용이 가능하다.
    // Session은 세션 서버 한개만을 사용하기 때문에 IOC컨테이너에 떠있다.
    // Request는 여러개의 요청이기 때문에 넣어두지 않았다. -> 그럼 IOC엔 한개만 사용 가능한 것들을 넣어두었을까?
    public User save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동으로 flush(트랜젝션 종료시)
        log.debug("디버그 : " + user.getId());// -> 보고싶으면 yml에가서 debug 켜놓고 보기
        em.persist(user);
        log.debug("디버그 : " + user.getId());// 영속화 후 (DB와 동기화된다.) -> persist context에 들어가는 순간 동기화 다시 나오면 똑같은 애들
        return user;
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public User findById(Long id) {
        return em.createQuery("select u from User u where u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}// where id로 한거