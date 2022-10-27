package site.matacoding.white.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User, Long> {// JpaRepository이 있으면 IOC에 등록이 된다.
    @Query(value = "select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    // findByEmail, findByGender
}
// 쿼리를 안적어도 자동으로 만들어줌
// 귀찮아도 쿼리에 적는게 좋음 -> 8번라인에 주석처리 하고 돌려도 똑같이 나오게 된다
// jpa부분은 테스트를 안해도 된다 -> 내가 쿼리를 적으면 테스트를 해봐야한다 (만들어져있는거면 안해도 된다)