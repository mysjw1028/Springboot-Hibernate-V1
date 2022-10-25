package site.matacoding.white.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.Board;
import site.matacoding.white.domain.BoardRepository;
import site.matacoding.white.domain.User;
import site.matacoding.white.domain.UserRepository;
import site.matacoding.white.dto.BoardReqDto.BoardSaveReqDto;

//트랜잭션 관리
// DTO 변환해서 컨트롤러에게 돌려줘야한다
// @Transactional // db에 영향을 주는거라서 무조건 걸어줘야한다

@RequiredArgsConstructor
@Service // IOC에 뜨게함
public class BoardService {// 트랜잭션 관리 위해서 만든거!! (다 만들어서 일관성을 맞춰준다)

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional // 직접 걸어줘야한다!
    public void save(BoardSaveReqDto boardSaveReqDto) {// boardRepository.save(board); // 1대1 매칭 / insert 하는거는 서비스 있어야한다
        User userPS = userRepository.findById(boardSaveReqDto.getSessionUser().getId());
        Board board = new Board();
        board.setTitle(boardSaveReqDto.getTitle());
        board.setContent(boardSaveReqDto.getContent());
        board.setUser(userPS);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true) // 세션 종료 안됨
    public Board findById(Long id) {
        System.out.println("최초select");
        Board boardPS = boardRepository.findById(id); // 오픈 인뷰가 false니까 조회후 세션 종료
        boardPS.getUser().getUsername(); // Lazy 로딩됨. (근데 Eager이면 이미 로딩되서 select 두번
        // 4. user select 됨?
        System.out.println("서비스단에서 지연로딩 함. 왜? 여기까지는 디비커넥션이 유지되니까");
        return boardPS;
    }

    @Transactional
    public void update(Long id, Board board) {
        Board boardPS = boardRepository.findById(id); // 영속화가 된거
        boardPS.setTitle(board.getTitle());
        boardPS.setContent(board.getContent());
    } // 트랜잭션 종료시 -> 더티체킹을 함
      // flush가 자동으로 된다 boardPS는 조회를 했기에 들고있다. 만약 없으면 insert를한다.

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional // db에 영향을 주는거라서 무조건 걸어줘야한다
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

}
// 통로 역할을 해준다.