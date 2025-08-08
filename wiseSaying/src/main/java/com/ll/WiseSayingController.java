package com.ll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.ll.entity.WiseSaying;
import com.ll.entity.WiseSaying;

class WiseSayingController {

    WiseSayingService ws = new WiseSayingService();
    public int connectDB() {
        if(ws.connectDB()==0){
            System.out.println("DB 연결 성공");
            return 0;
        } else {
            System.out.println("DB 연결 실패");
            return -1;
        }
    }

    public void insert() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("명언 : ");
            String quote = br.readLine();
            System.out.print("작가 : ");
            String writer = br.readLine();
            int id = ws.insert(new WiseSaying(0, quote, writer));
            System.out.println(id + "번 명언이 등록되었습니다.");
        } catch (Exception e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    public void showList(String commend) {

    }
    public HashMap<String, String> getKey(String commend){
        commend = commend.replace("목록?", "");
        String [] parts = commend.split("&");

        HashMap<String, String> map = new HashMap<>();
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2
                    && (keyValue[0].equals("page") || keyValue[0].equals("keyword") ||
                    keyValue[0].equals("keywordType"))
                    && keyValue[1] != null) {
                map.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return map;
    }
}