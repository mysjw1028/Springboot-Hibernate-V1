package site.matacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.Service.UserService;
import site.matacoding.white.domain.User;

@RequiredArgsConstructor
@RestController
public class UserApiController { // view는 플러터에서 할거라서 @Controller안씀

    public final UserService userService;
    public final HttpSession session;

    // join Dto
    @PostMapping("/join")
    public String save(@RequestBody User user) {
        userService.save(user);
        return "ok";
    }

    @PostMapping("/login") // us 인증관련된 주소는 도메인명을 안붙임 -> 인증에 관련된거는 인터셉터가 붙기 때문에 필터링이 필요함
    public String login(@RequestBody User user) {// Dto를 안받아서 착각할수가 있다
        User principal = userService.login(user);
        session.setAttribute("principal", principal);
        return "ok";
    }
}
