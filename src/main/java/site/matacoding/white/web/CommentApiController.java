package site.matacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.Service.CommentService;
import site.matacoding.white.dto.CommentReqDto.CommentSaveReqDto;
import site.matacoding.white.dto.ResponseDto;
import site.matacoding.white.dto.SessionUser;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final HttpSession session;

    @PostMapping("/comment")
    public ResponseDto<?> save(@RequestBody CommentSaveReqDto commentSaveReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }
        commentSaveReqDto.setSessionUser(sessionUser);
        return new ResponseDto<>(1, "성공", commentService.save(commentSaveReqDto));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseDto<?> deleteById(@PathVariable Long id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }
        commentService.deleteById(id);
        return new ResponseDto<>(1, "성공", null);
    }
}// 똑같이 생겨야 공통로직이 된다.