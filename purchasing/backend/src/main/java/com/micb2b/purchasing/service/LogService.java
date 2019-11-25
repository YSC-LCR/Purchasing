package com.micb2b.purchasing.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import com.micb2b.purchasing.domain.Log;

/**
 * @author jie
 * @date 2018-11-24
 */
public interface LogService {

    /**
     * 新增日誌
     * @param joinPoint
     * @param logEntity
     */
    @Async
    void save(ProceedingJoinPoint joinPoint, Log logEntity);
}
