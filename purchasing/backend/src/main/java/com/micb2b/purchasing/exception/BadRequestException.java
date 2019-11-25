package com.micb2b.purchasing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author jie
 * @date 2018-11-23
 * 統一異常處理
 */
@Getter
public class BadRequestException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status,String msg){
        super(msg);
        this.status = status.value();
    }
}
