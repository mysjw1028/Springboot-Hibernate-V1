package site.matacoding.white.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.Service.BoardService;
import site.matacoding.white.domain.Board;

@RequiredArgsConstructor
@RestController
public class BoardApiController { // view는 플러터에서 할거라서 @Controller안씀

    public final BoardService boardService;

    @GetMapping("/board/{id}")
    public Board findById(@PathVariable Long id) {
        return boardService.findById(id);// 오류난곳에 alt enteer
    }

    @GetMapping("/board")
    public List<Board> findAll() {
        return boardService.findAll();
    }

    @PutMapping("/board/{id}")
    public String update(@PathVariable Long id, @RequestBody Board board) {
        boardService.update(id, board);
        return "ok";
    }

    @PostMapping("/board")
    public String save(@RequestBody Board board) {
        boardService.save(board);
        return "ok";
    }// 사용자한테 json 타입 board를 받을거다 (포스트맨으로 테스트) insert

    @DeleteMapping("/board/{id}")
    public String deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
        return "ok";
    }

}
