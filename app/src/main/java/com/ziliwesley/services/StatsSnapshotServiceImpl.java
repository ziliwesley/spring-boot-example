package com.ziliwesley.services;

import com.ziliwesley.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statsSnapshotService")
public class StatsSnapshotServiceImpl implements StatsSnapshotService {

    @Autowired
    private VisitorRepository repository;

    /**
     * Get Total Visits (read from Database)
     * @return
     */
    private Long calcSumInDB() {
        return repository.countTotalVisits();
    }

    /**
     * Get Total Visits
     * @return
     */
    @Override
    public Long countTotalVisits() {
        return calcSumInDB();
    }
}
