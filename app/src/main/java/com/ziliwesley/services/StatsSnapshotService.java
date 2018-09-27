package com.ziliwesley.services;

import com.ziliwesley.entity.AppStats;
import com.ziliwesley.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("statsSnapshotService")
public class StatsSnapshotService implements IStatsSnapshotService {

    @Autowired
    private VisitorRepository repository;

    @Resource(name = "appStatsSnapshotService")
    private IAppStatsSnapshotService appStatsSnapshotService;

    /**
     * Get Total Visits (read from Database)
     * @return
     */
    private Long calcSumInDB() {
        Long total = repository.countTotalVisits();

        // In case no records found in database
        if (total == null) {
            total = 0L;
        }

        // Create a snapshot
        appStatsSnapshotService.saveSnapshot(total);

        return total;
    }

    /**
     * Get Total Visits
     * @return
     */
    @Override
    public Long countTotalVisits() {
        AppStats appStats = appStatsSnapshotService.getLatestSnapshot();

        if (appStats != null) {
            System.out.println("Found snapshot");
            return  appStats.getTotal();
        } else {
            System.out.println("Snapshot expires");
            return calcSumInDB();
        }
    }
}
