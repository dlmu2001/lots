package com.lucky.lots.entity;

public class LotUserBean {
    private Long lotId;
    private Long userId;
    private long id;
    private int lotNo;

    public LotUserBean(Long lotId, Long userId, long id, int lotNo) {
        this.lotId = lotId;
        this.userId = userId;
        this.id = id;
        this.lotNo = lotNo;
    }

    public Long getLotId() {
        return lotId;
    }

    public Long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public int getLotNo() {
        return lotNo;
    }
}
