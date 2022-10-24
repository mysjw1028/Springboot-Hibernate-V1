package site.matacoding.white.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.User;
import site.matacoding.white.domain.UserRepository;

//트랜잭션 관리
// DTO 변환해서 컨트롤러에게 돌려줘야한다
// @Transactional // db에 영향을 주는거라서 무조건 걸어줘야한다

@RequiredArgsConstructor
@Service // IOC에 뜨게함
public class UserService {// 트랜잭션 관리 위해서 만든거!! (다 만들어서 일관성을 맞춰준다)

    private final UserRepository userRepository;
    // private final EntityManager em;

    @Transactional // 트랜잭션을 붙이지 않으면 영속화 되어 있는 객체가 flush 가 안됨 / 실패하면 rollback이 됨
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // select하는데는 다 걸어야함
    public User login(User user) {
        User userPS = userRepository.findByUsername(user.getUsername());
        if (userPS.getPassword().equals(user.getPassword())) {
            return userPS;
        } else {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
    }// 트랜잭션 종료

}