package com.ziliwesley.controller;

import com.ziliwesley.services.IStatsSnapshotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Resource(name = "statsSnapshotService")
    private IStatsSnapshotService statsSnapshotService;

    /**
     * GET /stats/site
     * Return statistics of the site including total visits
     * @return
     */
    @RequestMapping(value = "/site", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> site() {
        Map<String, Object> result = new HashMap<String, Object>();
        Long totalVisits = statsSnapshotService.countTotalVisits();

        result.put("total", totalVisits);

        return result;
    }
}
