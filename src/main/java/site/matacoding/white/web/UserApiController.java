package site.matacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.Service.UserService;
import site.matacoding.white.dto.ResponseDto;
import site.matacoding.white.dto.SessionUser;
import site.matacoding.white.dto.UserReqDto.JoinReqDto;
import site.matacoding.white.dto.UserReqDto.LoginReqDto;
import site.matacoding.white.dto.UserRespDto.JoinRespDto;

@RequiredArgsConstructor
@RestController
public class UserApiController { // view는 플러터에서 할거라서 @Controller안씀

    public final UserService userService;
    public final HttpSession session;

    // 응답의 Dto는 서비스에서 만든다.
    @PostMapping("/join") // ResponseEntity 응답의 엔티티 -> 1 상태를 줘야함 (http)
    public ResponseDto<?> save(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.save(joinReqDto);
        return new ResponseDto<>(1, "ok", joinRespDto);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginReqDto loginReqDto) {
        SessionUser sessionUser = userService.login(loginReqDto);
        session.setAttribute("sessionUser", sessionUser);
        return new ResponseDto<>(1, "ok", sessionUser);

    }// us 인증관련된 주소는 도메인명을 안붙임 -> 인증에 관련된거는 인터셉터가 붙기 때문에 필터링이 필요함
} // Dto를 안받아서 착각할수가 있다
