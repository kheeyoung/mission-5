package com.ll.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;


import com.ll.entity.WiseSaying;
import com.ll.entity.WiseSaying;
import com.ll.service.WiseSayingService;

public class WiseSayingController {

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
        HashMap<String, String> keys= getKey(commend);

        String page = keys.remove("page");
        if(page == null){
            page = "1";
        }
        List<WiseSaying> data= ws.getList(keys, Integer.parseInt(page));

        if(data.isEmpty()){
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");
        for(WiseSaying ws : data){
            System.out.println(ws.getId() + " / " + ws.getWriter() + " / " + ws.getQuote());
        }

    }
    public HashMap<String, String> getKey(String commend){
        commend = commend.replace("목록?", "");
        String [] parts = commend.split("=");

        HashMap<String, String> map = new HashMap<>();

        //검색 하는 경우
        if(parts[0].equals("keywordType")){
            String[] key = parts[1].split("&");
            for(String k : key){
                map.put(k, parts[2]);
            }
            if(parts.length>3 && parts[3].startsWith("page")){
                map.put("page", parts[3]);
            }
        }

        //검색 없는 경우
        else if(parts[0].equals("page")){
            map.put("page", parts[1]);
        }

        return map;
    }

    public void delete(String commend) {
        String[] id = commend.split("=");
        if(id.length<2 || ! id[0].contains(("id"))) {
            System.out.println("정확한 id를 입력해주세요.");
            return;
        }
        if (ws.delete(Integer.parseInt(id[1])) <=0) {
            System.out.println(id[1]+"번 명언은 존재하지 않습니다.");
            return;
        }
        System.out.println(id[1]+"번 명언이 삭제되었습니다.");
    }
}