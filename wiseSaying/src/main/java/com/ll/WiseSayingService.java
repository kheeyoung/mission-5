package com.ll;

import com.ll.entity.WiseSaying;

class WiseSayingService {

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
}