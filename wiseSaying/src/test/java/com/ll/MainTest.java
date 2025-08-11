package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
public class MainTest {
    @Test
    @DisplayName("'== 명언 앱 ==' 출력")
    void runTest() throws IOException {
        String out = AppTestRunner.run("종료");
        assertThat(out).contains("== 명언 앱 ==");
    }

    @Test
    @DisplayName("등록 테스트")
    void insertTest() throws IOException {
        // Given
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);
        assertThat(out).contains("명령) ");
        assertThat(out).contains("명언 : ");
        assertThat(out).contains("작가 : ");
    }

    @Test
    @DisplayName("목록 테스트")
    void listTest() throws IOException {
        // Given
        String out = AppTestRunner.run("""
                목록
                종료
                """);
        assertThat(out).contains("번호 / 작가 / 명언");
        assertThat(out).contains("----------------------");

    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() throws IOException {
        String out = AppTestRunner.run("""
                삭제?id=7
                종료
                """);
        assertThat(out).contains("명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("수정 테스트")
    void updateTest() throws IOException {
        String out = AppTestRunner.run("""
                수정?id=5
                now를 사랑하라.
                작자미상?
                종료
                """);
        assertThat(out).contains("명령) ");
        assertThat(out).contains("명언 : ");
        assertThat(out).contains("작가 : ");

        assertThat(out).contains("명언(기존) : ");
        assertThat(out).contains("작가(기존) : ");
    }

    @Test
    @DisplayName("빌드 테스트")
    void buildTest() throws IOException {
        String out = AppTestRunner.run("""
                빌드
                종료
                """);
        assertThat(out).contains("파일의 내용이 갱신되었습니다.");
    }
}
