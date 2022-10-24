package site.matacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.matacoding.white.domain.User;

public class BoardReqDto {

    @Getter
    @Setter
    public static class BoardSaveDto {
        private String title;
        private String content;
        private User user;// 서비스 로직
    }
    // 필요한 DTO는 여기에 추가
}