package site.matacoding.white.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.Service.BoardService;
import site.matacoding.white.domain.Board;
import site.matacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.matacoding.white.dto.SessionUser;

@RequiredArgsConstructor
@RestController // Api를 만들기 때문에 Rest로 (Rest는 JSON을 리턴)
public class BoardApiController {

    private final BoardService boardService;
    private final HttpSession session;

    // 자동 import : alt+ shift+ o
    // @PostMapping("/board")
    // public String save(@RequestBody Board board) {
    // boardService.save(board);
    // return "ok";
    // }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id) {
        System.out.println("현재 open-in-view는 true 인가 false 인가 생각해보기!!");
        Board boardPS = boardService.findById(id);
        System.out.println("board.id : " + boardPS.getId());
        System.out.println("board.title : " + boardPS.getTitle());
        System.out.println("board.content : " + boardPS.getContent());
        System.out.println("open-in-view가 false이면 Lazy 로딩 못함");
        // 날라감)
        return "ok";
    }

    public String save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        // insert into board(title,content,user_id) values(?, ?, ?)
        boardSaveReqDto.setSessionUser(sessionUser);
        boardService.save(boardSaveReqDto); // 서비스에는 단 하나의 객체만 전달한다.
        return "ok";
    }

    @PutMapping("/board/{id}")
    public String update(@PathVariable Long id, @RequestBody Board board) {
        boardService.update(id, board);
        return "ok";
    }

    @DeleteMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deleteById(id);
        return "ok";
    }

    @GetMapping("/board")
    public List<Board> findAll() {
        List<Board> boardPS = boardService.findAll();
        return boardPS;
    }

}
