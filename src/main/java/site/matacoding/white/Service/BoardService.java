package site.matacoding.white.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.Board;
import site.matacoding.white.domain.BoardRepository;

@RequiredArgsConstructor
@Service // IOC에 뜨게함
public class BoardService {// 트랜잭션 관리 위해서 만든거!! (다 만들어서 일관성을 맞춰준다)

    private final BoardRepository boardRepository;

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional // 직접 걸어줘야한다!
    public void save(Board board) {
        boardRepository.save(board); // 1대1 매칭 / insert 하는거는 서비스 있어야한다
    }

    @Transactional
    public void update(Long id, Board board) {
        Board boardPS = boardRepository.findById(id); // 영속화가 된거
        boardPS.setTitle(board.getTitle());
        boardPS.setContent(board.getContent());
        boardPS.setAuthor(board.getAuthor());
    } // 트랜잭션 종료시 -> 더티체킹을 함
      // flush가 자동으로 된다 boardPS는 조회를 했기에 들고있다. 만약 없으면 insert를한다.

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

}
// 통로 역할을 해준다.