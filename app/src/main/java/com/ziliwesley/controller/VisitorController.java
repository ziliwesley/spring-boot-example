package com.ziliwesley.controller;

import com.ziliwesley.entity.Visitor;
import com.ziliwesley.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VisitorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VisitorRepository repository;

    /**
     * GET /
     * Index page of the application
     * @param request
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Visitor visitor = repository.findByIp(ip);

        if (visitor == null) {
            visitor = new Visitor();
            visitor.setIp(ip);
            visitor.setTimes(1);
        } else {
            visitor.setTimes(visitor.getTimes() + 1);
        }

        logger.info("New visit from: {}", ip);

        repository.save(visitor);

        return "Welcome! (" + visitor.getIp() + ": " + visitor.getTimes() + ")";
    }
}
