package com.ziliwesley.entity;

import com.ziliwesley.constant.AppStatsConfig;

import java.io.Serializable;

public class AppStats implements Serializable {

    private Long total; // Total visitors

    private Long updated; // Timestamp of last update

    public AppStats(Long total, Long updated) {
        this.total = total;
        this.updated = updated;
    }

    /**
     * Return if the statistics is still valid
     * @return
     */
    public boolean hasExpired() {
        return System.currentTimeMillis() - this.getUpdated() > AppStatsConfig.STATS_SNAPSHOT_DURATION;
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
