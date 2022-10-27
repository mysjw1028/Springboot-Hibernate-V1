package site.matacoding.white.web;

import javax.swing.text.Document;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import site.matacoding.white.dto.UserReqDto.JoinReqDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // 통합테스트 랜덤으로 돌려서 테스트
public class UserApiController {

    // @Autowired //기존것에도 띄울수있다. 디펜더신인젝션 코드
    // private UserService userService;
    @Autowired
    private TestRestTemplate rt;

    private static ObjectMapper om;

    private static HttpHeaders httpheaders;

    @BeforeAll // BeforeAll를 걸면 무조건 static을 걸어야한다
    public static void init() {
        om = new ObjectMapper();// json
        httpheaders = new HttpHeaders();// http요청 header에 필요
        httpheaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void join_test() throws JsonProcessingException {// 오류 날수 있어서 찍어보기
        // given
        JoinReqDto joinReqDto = new JoinReqDto(); // given 데이터
        joinReqDto.setUsername("hoho2");
        joinReqDto.setUsername("1234");

        String json = om.writeValueAsString(joinReqDto); // json으로 변환이 됨
        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = rt.exchange("/join", HttpMethod.POST, request, String.class);
        // then
        // System.out.println(response.getStatusCode());
        // System.out.println(response.getBody());

        DocumentContext dc = JsonPath.parse(response.getBody());
        int code = dc.read("$.code");
        Assertions.assertThat(code).isEqualTo(1);
    }

}
// junit은 메서드 단위로 띄워서 테스트
// 언더스토어를 쓴다-> 컴멜도 되지만 팀원들이랑 똑같이 해야한다.