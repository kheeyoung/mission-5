package com.ll;

import com.ll.controller.WiseSayingController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    WiseSayingController wc = new WiseSayingController();
    public void run() throws IOException {

        if(wc.connectDB()==-1){
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) :");
            String commend = br.readLine();
            switch (commend.substring(0,2)) {
                case "종료":
                    System.out.println("앱을 종료합니다.");
                    return;

                case "목록":
                    wc.showList(commend);
                    break;

                case "등록":
                    wc.insert();
                    break;

                case "수정":

                    break;

                case "삭제":

                    break;

                case "빌드":

                    break;

                default:
                    System.out.println("알 수 없는 명령입니다. 다시 시도하세요.");
            }
        }
    }
}
