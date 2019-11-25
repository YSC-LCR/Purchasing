package com.micb2b.purchasing.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.micb2b.purchasing.exception.BadRequestException;
import com.micb2b.purchasing.exception.EntityExistException;
import com.micb2b.purchasing.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jie
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 處理所有不可知的異常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        // 列印堆疊資訊
    	log.error(e.getMessage(), e);
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 處理 介面無權訪問異常AccessDeniedException
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e){
        // 列印堆疊資訊
    	log.error(e.getMessage(), e);
        ApiError apiError = new ApiError(FORBIDDEN.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 處理自訂異常
     * @param e
     * @return
     */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 列印堆疊資訊
        log.error(e.getMessage(), e);
        ApiError apiError = new ApiError(e.getStatus(),e.getMessage());
        return buildResponseEntity(apiError);
	}

    /**
     * 處理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 列印堆疊資訊
    	log.error(e.getMessage(), e);
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 處理 EntityNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 列印堆疊資訊
    	log.error(e.getMessage(), e);
        ApiError apiError = new ApiError(NOT_FOUND.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 處理所有介面資料驗證異常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 列印堆疊資訊
    	log.error(e.getMessage(), e);
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1]+":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        ApiError apiError = new ApiError(BAD_REQUEST.value(),msg.toString());
        return buildResponseEntity(apiError);
    }

    /**
     * 統一返回
     * @param apiError
     * @return
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
