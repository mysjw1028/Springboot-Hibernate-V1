package site.matacoding.white.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Comment {// 스칼라로 표현 못하는것은 오브젝트로 빼기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    // User 누가 썼는지
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // Board 어디에 썼는지
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public Comment(Long id, String content, User user, Board board) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.board = board;
    }

}
// 하나의 게시글에는 여러개의 댓글을 달수있음
// fk를 안걸려고 yml run으로 해서 fk 를 다 날림