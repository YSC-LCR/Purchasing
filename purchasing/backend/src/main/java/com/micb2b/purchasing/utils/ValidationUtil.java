package com.micb2b.purchasing.utils;

import java.util.Optional;

import com.micb2b.purchasing.exception.BadRequestException;


/**
 * 驗證工具
 * @author jie
 * @date 2018-11-23
 */
public class ValidationUtil{

    /**
     * 驗證空
     * @param optional
     */
    public static void isNull(Optional optional, String entity, String parameter, Object value){
        if(!optional.isPresent()){
            String msg = entity
                         + " 不存在 "
                         +"{ "+ parameter +":"+ value.toString() +" }";
            throw new BadRequestException(msg);
        }
    }

    /**
     * 驗證是否為郵箱
     * @param string
     * @return
     */
    public static boolean isEmail(String string) {
        if (string == null){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return string.matches(regEx1);
    }
}
