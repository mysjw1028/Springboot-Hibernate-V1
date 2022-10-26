package site.matacoding.white.web;

import javax.servlet.http.HttpSession;

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
        commentSaveReqDto.setSessionUser(sessionUser);
        return new ResponseDto<>(1, "성공", commentService.save(commentSaveReqDto));
    }
}