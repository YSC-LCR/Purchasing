package com.micb2b.purchasing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micb2b.purchasing.aop.Log;
import com.micb2b.purchasing.domain.User;
import com.micb2b.purchasing.dto.UserDTO;
import com.micb2b.purchasing.exception.BadRequestException;
import com.micb2b.purchasing.security.JwtUser;
import com.micb2b.purchasing.service.UserService;
import com.micb2b.purchasing.service.query.UserQueryService;
import com.micb2b.purchasing.utils.EncryptUtils;
import com.micb2b.purchasing.utils.JwtTokenUtil;
import com.micb2b.purchasing.utils.SecurityContextHolder;

/**
 * @author jie
 * @date 2018-11-23
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    private static final String ENTITY_NAME = "user";

    @Log("用戶管理-查詢用戶")
    @GetMapping(value = "/users")
    public ResponseEntity getUsers(UserDTO userDTO, Pageable pageable){
        return new ResponseEntity(userQueryService.queryAll(userDTO,pageable),HttpStatus.OK);
    }

    @Log("用戶管理-新增用戶")
    @PostMapping(value = "/users")
    public ResponseEntity create(@Validated @RequestBody User resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(userService.create(resources),HttpStatus.CREATED);
    }

    @Log("用戶管理-修改用戶")
    @PutMapping(value = "/users")
    public ResponseEntity update(@Validated(User.Update.class) @RequestBody User resources){
        userService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("用戶管理-刪除用戶")
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 驗證密碼
     * @param pass
     * @return
     */
    @GetMapping(value = "/users/validPass/{pass}")
    public ResponseEntity validPass(@PathVariable String pass){
        UserDetails userDetails = SecurityContextHolder.getUserDetails();
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(userDetails.getUsername());
        Map map = new HashMap();
        map.put("status",200);
        if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(pass))){
           map.put("status",400);
        }
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 修改密碼
     * @param pass
     * @return
     */
    @GetMapping(value = "/users/updatePass/{pass}")
    public ResponseEntity updatePass(@PathVariable String pass){
        UserDetails userDetails = SecurityContextHolder.getUserDetails();
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(userDetails.getUsername());
        if(jwtUser.getPassword().equals(EncryptUtils.encryptPassword(pass))){
            throw new BadRequestException("新密碼不能與舊密碼相同");
        }
        userService.updatePass(jwtUser,EncryptUtils.encryptPassword(pass));
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 修改郵箱
     * @param user
     * @param user
     * @return
     */
    @PostMapping(value = "/users/updateEmail/{code}")
    public ResponseEntity updateEmail(@PathVariable String code,@RequestBody User user){
        UserDetails userDetails = SecurityContextHolder.getUserDetails();
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(userDetails.getUsername());
        if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(user.getPassword()))){
            throw new BadRequestException("密碼錯誤");
        }
//        VerificationCode verificationCode = new VerificationCode(code, ElAdminConstant.RESET_MAIL,"email",user.getEmail());
//        verificationCodeService.validated(verificationCode);
        userService.updateEmail(jwtUser,user.getEmail());
        return new ResponseEntity(HttpStatus.OK);
    }
}
