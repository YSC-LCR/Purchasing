package com.micb2b.purchasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micb2b.purchasing.domain.Log;
import com.micb2b.purchasing.service.query.LogQueryService;

/**
 * @author jie
 * @date 2018-11-24
 */
@RestController
@RequestMapping("api")
public class LogController {

    @Autowired
    private LogQueryService logQueryService;

    @GetMapping(value = "/logs")
    public ResponseEntity getLogs(Log log, Pageable pageable){
        log.setLogType("INFO");
        return new ResponseEntity(logQueryService.queryAll(log,pageable), HttpStatus.OK);
    }
}
