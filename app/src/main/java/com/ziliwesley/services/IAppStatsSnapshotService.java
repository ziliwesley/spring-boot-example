package com.ziliwesley.services;

import com.ziliwesley.entity.AppStats;

public interface IAppStatsSnapshotService {

    /**
     * Create or update app statistics
     * @param count
     */
    void saveSnapshot(Long count);

    /**
     * Retrive current snapshot in cache
     * @return
     */
    AppStats getSnapshot();


    /**
     * Get lastest snapshot, valid within a specific period of time;
     * return null if expires
     * @return
     */
    AppStats getLatestSnapshot();
}
