package com.micb2b.purchasing.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.micb2b.purchasing.domain.Log;
import com.micb2b.purchasing.repository.LogRepository;
import com.micb2b.purchasing.service.LogService;
import com.micb2b.purchasing.utils.SecurityContextHolder;
import com.micb2b.purchasing.utils.StringUtils;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jie
 * @date 2018-11-24
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    private final String LOGINPATH = "login";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProceedingJoinPoint joinPoint, Log logEntity){

        // 獲取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
       
        // 方法路徑
        String methodName = joinPoint.getTarget().getClass().getSimpleName()+"."+signature.getName();

        String params = "{";
        //參數值
        Object[] argValues = joinPoint.getArgs();
        //參數名稱
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 用戶名
        String username = "";

        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }

        // 獲取IP地址
        logEntity.setRequestIp(StringUtils.getIP(request));

        if(!LOGINPATH.equals(signature.getName())){
            UserDetails userDetails = SecurityContextHolder.getUserDetails();
            username = userDetails.getUsername();
        } else {
            try {
                JSONObject jsonObject = new JSONObject(argValues[0]);
                username = jsonObject.get("username").toString();
            }catch (Exception e){
            	log.error(e.getMessage(), e);
            }
        }
        logEntity.setMethod(methodName);
        logEntity.setUsername(username);
        logEntity.setParams(params + " }");
        logRepository.save(logEntity);
    }
}
