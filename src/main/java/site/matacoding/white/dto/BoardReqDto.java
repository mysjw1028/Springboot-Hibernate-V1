package site.matacoding.white.dto;

import lombok.Getter;
import lombok.Setter;

public class BoardReqDto {

    @Getter
    @Setter
    public static class BoardSaveReqDto {
        private String title;
        private String content;
        private SessionUser sessionUser;// 서비스 로직 Dto로 변경
    }
    // 필요한 DTO는 여기에 추가
}