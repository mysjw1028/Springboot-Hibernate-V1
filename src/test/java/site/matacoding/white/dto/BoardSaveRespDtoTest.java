package site.matacoding.white.dto;

import org.junit.jupiter.api.Test;

import site.matacoding.white.domain.Board;
import site.matacoding.white.dto.BoardRespDto.BoardSaveRespDto;

public class BoardSaveRespDtoTest {

    @Test
    public void innerclass_테스트() {
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(new Board());
    }
}