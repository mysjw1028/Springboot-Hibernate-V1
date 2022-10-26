package site.matacoding.white.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.matacoding.white.domain.Board;
import site.matacoding.white.domain.BoardRepository;
import site.matacoding.white.domain.Comment;
import site.matacoding.white.domain.CommentRepository;
import site.matacoding.white.dto.CommentReqDto.CommentSaveReqDto;
import site.matacoding.white.dto.CommentRespDto.CommentSaveRespDto;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentSaveRespDto save(CommentSaveReqDto commentSaveReqDto) {
        Optional<Board> boardOP = boardRepository.findById(commentSaveReqDto.getBoardId());
        if (boardOP.isPresent()) {
            Comment comment = commentSaveReqDto.toEntity(boardOP.get());
            Comment commentPS = commentRepository.save(comment);
            CommentSaveRespDto commentSaveRespDto = new CommentSaveRespDto(commentPS);
            return commentSaveRespDto;
        } else {
            throw new RuntimeException("게시글이 없어서 댓글을 쓸 수 없습니다.");
        }
    }
}// session은 예외->어차피 잘못될리가 없어서 디비에 이상한 데이터가 들어갈일이 없다
 // 다시 select를 해서 들고오는게 제일 안전하다, -> 뭐를 넣으면 영속화해서 넣을것
