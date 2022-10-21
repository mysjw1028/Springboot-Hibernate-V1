package site.matacoding.white.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.Board;
import site.matacoding.white.domain.BoardRepository;

@RequiredArgsConstructor
@Service // IOC에 뜨게함
public class BoardService {// 트랜잭션 관리 위해서 만든거!! (다 만들어서 일관성을 맞춰준다)

    private final BoardRepository boardRepository;

    @Transactional // 직접 걸어줘야한다!
    public void save(Board board) {
        boardRepository.save(board); // 1대1 매칭 / insert 하는거는 서비스 있어야한다
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }
}
// 통로 역할을 해준다.