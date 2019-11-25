package com.micb2b.purchasing.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

import com.micb2b.purchasing.exception.BadRequestException;


/**
 * 獲取當前登錄的用戶名
 *
 * @author jie
 * @date 2019-01-17
 */
public class SecurityContextHolder {

    public static UserDetails getUserDetails() {
        UserDetails userDetails = null;
        try {
            SecurityContext securityContext = org.springframework.security.core.context.SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            userDetails = (UserDetails)authentication.getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登錄狀態過期");
        }
        return userDetails;
    }
}
