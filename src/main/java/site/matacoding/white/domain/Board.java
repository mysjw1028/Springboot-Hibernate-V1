package site.matacoding.white.domain;

import javax.persistence.Column;
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
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 DB의 번호 증가전략을 따라간다
    private Long id;
    private String title;
    @Column(length = 1000)
    private String content;

    // FK가 만들어짐 .user_id
    @ManyToOne(fetch = FetchType.LAZY) // 관계를 적으면 안터진다 fk를 걸어줄때 이때 1:N관계를 확인하고 N쪽으로 fk를 걸어줘야한다
    private User user;// 이거만 적으면 터짐

    @Builder
    public Board(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 변경하는 코드는 의미 있게 메서드로 구현 -> setter로 하면 아이디도 변경해야하서 이런식으로 해야한다.
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}