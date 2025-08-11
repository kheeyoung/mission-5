package com.ll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

public class MainTest {
    @Test
    @DisplayName("등록 테스트")
    void insertTest() throws IOException {
        // Given
        App app = new App();
        String input = "등록\n명언1\n작가1\n종료\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        app.run();

        String output = outContent.toString();
        System.out.println("=== 테스트 출력 ===");
        System.out.println(output);

        Assertions.assertTrue(output.contains("명언이 등록되었습니다."));
    }

    @Test
    @DisplayName("목록 테스트")
    void listTest() throws IOException {
        // Given
        App app = new App();
        String input = "목록\n종료\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        app.run();

        String output = outContent.toString();
        System.out.println("=== 테스트 출력 ===");
        System.out.println(output);

        Assertions.assertTrue(output.contains("번호 / 작가 / 명언"));
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() throws IOException {
        // Given
        App app = new App();
        String input = "삭제?id=1\n종료\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // 실행
        app.run();
    }

    @Test
    @DisplayName("빌드 테스트")
    void buildTest() throws IOException {
        // Given
        App app = new App();
        String input = "빌드\n종료\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // 실행
        app.run();
    }
}
