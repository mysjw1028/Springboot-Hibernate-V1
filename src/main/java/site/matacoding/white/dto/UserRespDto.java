package site.matacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.matacoding.white.domain.User;

public class UserRespDto {

    @Setter
    @Getter
    public static class JoinRespDto {
        private Long id;
        private String username;

        // 응답의 Dto는 생성자로 처리한다
        public JoinRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
        }
    }
}