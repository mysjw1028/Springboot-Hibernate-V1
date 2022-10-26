package site.matacoding.white.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.Board;
import site.matacoding.white.domain.BoardRepository;
import site.matacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.matacoding.white.dto.BoardReqDto.BoardUpdateReqDto;
import site.matacoding.white.dto.BoardRespDto.BoardAllRespDto;
import site.matacoding.white.dto.BoardRespDto.BoardDetailRespDto;
import site.matacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.matacoding.white.dto.BoardRespDto.BoardUpdateRespDto;

//트랜잭션 관리
// DTO 변환해서 컨트롤러에게 돌려줘야한다
// @Transactional // db에 영향을 주는거라서 무조건 걸어줘야한다

@RequiredArgsConstructor
@Service // IOC에 뜨게함
public class BoardService {// 트랜잭션 관리 위해서 만든거!! (다 만들어서 일관성을 맞춰준다)

    private final BoardRepository boardRepository;

    @Transactional // 직접 걸어줘야한다!
    public BoardSaveRespDto save(BoardSaveReqDto boardSaveReqDto) {// boardRepository.save(board); // 1대1 매칭 / insert
        // 핵심로직
        Board boardPS = boardRepository.save(boardSaveReqDto.toEntity());
        // DB에서 들고온다 -> 영속화 된 애를 넣는다.

        // Dto전환
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(boardPS);

        return boardSaveRespDto;
    }// 다시 select를 해서 들고오는게 제일 안전하다, -> 뭐를 넣으면 영속화해서 넣을것

    @Transactional(readOnly = true) // 트랜잭션을 걸면 OSIV가 false여도 디비 커넥션이 유지됨.
    public BoardDetailRespDto findById(Long id) {
        Optional<Board> boardOP = boardRepository.findById(id);// 오픈 인뷰가 false니까 조회후 세션 종료
        if (boardOP.isPresent()) {
            BoardDetailRespDto boardDetailRespDto = new BoardDetailRespDto(boardOP.get());
            return boardDetailRespDto;
        } else {
            throw new RuntimeException("해당" + id + "로 상세보기를 할 수 없습니다.");
        } // 4. user select 됨?// Lazy 로딩됨. (근데 Eager이면 이미 로딩되서 select 두번된다. /필드까지 때려라 가능하다

    }

    @Transactional
    public BoardUpdateRespDto update(BoardUpdateReqDto boardUpdateReqDto) {
        Long id = boardUpdateReqDto.getId();
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isEmpty()) {
            throw new RuntimeException("해당 " + id + "로 수정을 할 수 없습니다");
        }
        Board boardPS = boardOP.get();
        boardPS.update(boardUpdateReqDto.getTitle(), boardUpdateReqDto.getContent());
        return new BoardUpdateRespDto(boardPS);
    } // 트랜잭션 종

    // 트랜잭션 종료시 -> 더티체킹을 함
    // flush가 자동으로 된다 boardPS는 조회를 했기에 들고있다. 만약 없으면 insert를한다.

    @Transactional(readOnly = true)
    public List<BoardAllRespDto> findAll() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardAllRespDto> boardAllRespDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardAllRespDtoList.add(new BoardAllRespDto(board));
        }

        return boardAllRespDtoList;
    }

    // 1. list의 크기만큼 for문 돌리기
    // 2. Board -> DTO로 옮김
    // 3. DTO를 List에 담기

    @Transactional // db에 영향을 주는거라서 무조건 걸어줘야한다
    public void deleteById(Long id) {
        Optional<Board> boardOP = boardRepository.findById(id);// 오픈 인뷰가 false니까 조회후 세션 종료
        if (boardOP.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new RuntimeException("해당 " + id + "로 삭제를 할 수 없습니다.");
        } // 4. user select 됨?// Lazy 로딩됨. (근데 Eager이면 이미 로딩되서 select 두번된다. /필드까지 때려라 가능하다

    }
}
// 통로 역할을 해준다.