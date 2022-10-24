package site.matacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
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
    @ManyToOne(fetch = FetchType.EAGER) // 관계를 적으면 안터진다 fk를 걸어줄때 이때 1:N관계를 확인하고 N쪽으로 fk를 걸어줘야한다
    private User user;// 이거만 적으면 터짐
}