package com.ll.service;

import com.ll.entity.WiseSaying;
import com.ll.repository.WiseSayingRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class WiseSayingService {

    WiseSayingRepository wr = new WiseSayingRepository();

    public int connectDB() {
        return wr.connectDB();
    }

    public int insert(WiseSaying ws) {
        try {
            ws.setId(wr.getNum() + 1);
            wr.insert(ws);
            return ws.getId();
        } catch (Exception e) {
            System.out.println("명언 등록 실패: " + e.getMessage());
            return -1;
        }
    }

    public List<WiseSaying> getList(HashMap<String, String> keys, int i) {
        //검색 조건이 없을 때
        if( keys.isEmpty()) {
            return wr.getList(i);
        }
        //검색 조건이 있을 때
        return wr.searchList(keys, i);
    }

    public int delete(int i) {
        return wr.delete(i);
    }

    public List<WiseSaying> getAlldata() {
        return wr.getAllData();
    }

    public int makeFile(List<WiseSaying> data) {


        // 파일 출력
        try (FileWriter file = new FileWriter("build.json")) {
            String content="";
            for(WiseSaying ws : data) {
                content += "{\n" +
                        "  \"id\": " + ws.getId() + ",\n" +
                        "  \"quote\": \"" + ws.getQuote() + "\",\n" +
                        "  \"writer\": \"" + ws.getWriter() + "\"\n" +
                        "},\n";
            }
            file.write(content);
            return 0;
        } catch (IOException e) {
            return -1;
        }
    }
}