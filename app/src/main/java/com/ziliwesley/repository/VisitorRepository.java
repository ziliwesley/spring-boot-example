package com.ziliwesley.repository;

import com.ziliwesley.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    /**
     * Find a visitor by IP address
     * @param ip
     * @return
     */
    Visitor findByIp(String ip);

    /**
     * Count total visits
     * @return
     */
    @Query(
        value = "SELECT SUM(v.times) FROM visitor v",
        nativeQuery = true
    )
    Long countTotalVisits();
}
