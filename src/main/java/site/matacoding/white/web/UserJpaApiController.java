package site.matacoding.white.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.User;
import site.matacoding.white.domain.UserJpaRepository;
import site.matacoding.white.dto.ResponseDto;

@RequiredArgsConstructor
@RestController
public class UserJpaApiController {
    private final UserJpaRepository userJpaRepository;

    // findById , findAll, sava, deleteById -> 기본적인 crud는 만들어져있다.

    @GetMapping("/jpa/user/{id}")
    public ResponseDto<?> findById(@PathVariable Long id) {
        Optional<User> userOP = userJpaRepository.findById(id);// optinal를 리턴함
        if (userOP.isPresent()) {
            return new ResponseDto<>(1, "성공", userOP.get());
        } else {
            throw new RuntimeException("해당사용자를 찾을수 없습니다,");
        }
    }

    @PostMapping("/jpa/join")
    public ResponseDto<?> save(@RequestBody User user) {
        User userPS = userJpaRepository.save(user);
        return new ResponseDto<>(1, "성공", userPS);
    }

    @PostMapping("/jpa/login")
    public ResponseDto<?> login(@RequestBody User user) {
        User userPS = userJpaRepository.findByUsername(user.getUsername());
        if (userPS.getPassword().equals(user.getPassword())) {
            return new ResponseDto<>(1, "성공", userPS);
        }
        throw new RuntimeException("아이디 비밀번호가 틀렸습니다,");

    }

    @GetMapping("/jpa/user")
    public ResponseDto<?> findAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 2);
        Page<User> userList = userJpaRepository.findAll(pageRequest);
        return new ResponseDto<>(1, "성공", userList);
    }
}
