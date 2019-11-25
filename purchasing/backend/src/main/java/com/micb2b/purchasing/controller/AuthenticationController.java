package com.micb2b.purchasing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micb2b.purchasing.aop.Log;
import com.micb2b.purchasing.security.AuthenticationInfo;
import com.micb2b.purchasing.security.AuthorizationUser;
import com.micb2b.purchasing.security.JwtUser;
import com.micb2b.purchasing.utils.EncryptUtils;
import com.micb2b.purchasing.utils.JwtTokenUtil;
import com.micb2b.purchasing.utils.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;


/**
 * @author RJ
 * 授權、根據token獲取使用者詳細資訊
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 登錄授權
     * @param authorizationUser
     * @return
     */
    @Log("用戶登錄")
    @PostMapping(value = "${jwt.auth.path}")// /login
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser){
    	
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());
        logger.info("jwtUser.getPassword:{}", jwtUser.getPassword());
        logger.info("authorizationUser.getPassword:{}", authorizationUser.getPassword());
        logger.info("EncryptUtils.encryptPassword:{}", EncryptUtils.encryptPassword(authorizationUser.getPassword()));
        if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))){
            throw new AccountExpiredException("密碼錯誤");
        }

        if(!jwtUser.isEnabled()){
            throw new AccountExpiredException("帳號已停用，請聯繫管理員");
        }

        // 生成權杖
        final String token = jwtTokenUtil.generateToken(jwtUser);

        // 返回 token
        return ResponseEntity.ok(new AuthenticationInfo(token,jwtUser));
    }

    /**
     * 獲取使用者資訊
     * @return
     */
    @GetMapping(value = "${jwt.auth.account}")
    public ResponseEntity getUserInfo(){
        UserDetails userDetails = SecurityContextHolder.getUserDetails();
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(jwtUser);
    }
}
