package com.ziliwesley.services;

import com.ziliwesley.constant.AppStatsConfig;
import com.ziliwesley.entity.AppStats;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("appStatsSnapshotService")
public class AppStatsSnapshotService implements IAppStatsSnapshotService {

    private final String REDIS_KEY = "app_stats";

    private StringRedisTemplate redisTemplate;

    HashOperations hashOperations;

    public AppStatsSnapshotService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveSnapshot(Long count) {
        Long now = System.currentTimeMillis();

        hashOperations.put(REDIS_KEY, "total", String.valueOf(count));
        hashOperations.put(REDIS_KEY, "updated", String.valueOf(now));
    }

    @Override
    public AppStats getSnapshot() {
        Map<String, String> hash = hashOperations.entries(REDIS_KEY);

        if (hash.containsKey("total") &&
            hash.containsKey("updated")) {
            Long total = Long.valueOf(hash.get("total"));
            Long updated = Long.valueOf(hash.get("updated"));

            return new AppStats(total, updated);
        }

        return null;
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
