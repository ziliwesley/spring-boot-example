package com.ziliwesley.entity;

import java.io.Serializable;

public class AppStats implements Serializable {

    private Long total; // Total visitors

    private Long updated; // Timestamp of last update

    public AppStats(Long total, Long updated) {
        this.total = total;
        this.updated = updated;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }
}
