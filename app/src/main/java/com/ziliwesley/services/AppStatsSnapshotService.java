package com.ziliwesley.services;

import com.ziliwesley.constant.AppStatsConfig;
import com.ziliwesley.entity.AppStats;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("appStatsSnapshotService")
public class AppStatsSnapshotService implements IAppStatsSnapshotService {

    private final String REDIS_KEY = "app_stats";

    public AppStatsSnapshotService(RedisTemplate<String, Long> longRedisTemplate) {
        this.longRedisTemplate = longRedisTemplate;
        this.hashOperations = longRedisTemplate.opsForHash();
    }

    private RedisTemplate<String, Long> longRedisTemplate;

    HashOperations hashOperations;

    @Override
    public void saveSnapshot(Long count) {
        Long now = System.currentTimeMillis();

        hashOperations.put(REDIS_KEY, "total", count);
        hashOperations.put(REDIS_KEY, "updated", now);
    }

    @Override
    public AppStats getSnapshot() {
        Long total = (Long)hashOperations.get(REDIS_KEY, "total");
        Long updated = (Long)hashOperations.get(REDIS_KEY, "updated");

        if (total != null && updated != null) {
            return new AppStats(total, updated);
        } else {
            return null;
        }
    }

    @Override
    public AppStats getLatestSnapshot() {
        AppStats appStats = getSnapshot();

        if (appStats != null) {
            Long timeElapsed = System.currentTimeMillis() - appStats.getUpdated();
            System.out.println("Elapsed " + timeElapsed);
            if (timeElapsed > AppStatsConfig.STATS_SNAPSHOT_DURATION) {
                // Already Expired
                return null;
            }
        }

        return appStats;
    }
}
