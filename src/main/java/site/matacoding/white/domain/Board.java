package site.matacoding.white.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String content;

    // FK가 만들어짐 .user_id
    @ManyToOne(fetch = FetchType.LAZY) // 관계를 적으면 안터진다 fk를 걸어줄때 이때 1:N관계를 확인하고 N쪽으로 fk를 걸어줘야한다
    private User user;// 이거만 적으면 터짐 / 한건만 있어서 비용이 적게듬

    // 조회를 위해서만 필요함
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    // Many는 fk가 될수가 없다//mappedBy의 주인이 누구인지 board를 셀렉하면 땡겨온다

    @Builder
    public Board(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 변경하는 코드는 의미 있게 메서드로 구현
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}