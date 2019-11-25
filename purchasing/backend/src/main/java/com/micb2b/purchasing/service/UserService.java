package com.micb2b.purchasing.service;

import com.micb2b.purchasing.domain.User;
import com.micb2b.purchasing.dto.UserDTO;
import com.micb2b.purchasing.security.JwtUser;

/**
 * @author jie
 * @date 2018-11-23
 */
public interface UserService {

    /**
     * get
     * @param id
     * @return
     */
    UserDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    UserDTO create(User resources);

    /**
     * update
     * @param resources
     */
    void update(User resources);

    /**
     * delete
     * @param id
     */
    void delete(Long id);

    /**
     * findByName
     * @param userName
     * @return
     */
    User findByName(String userName);

    /**
     * 修改密码
     * @param jwtUser
     * @param encryptPassword
     */
    void updatePass(JwtUser jwtUser, String encryptPassword);

    /**
     * 修改头像
     * @param jwtUser
     * @param url
     */
    void updateAvatar(JwtUser jwtUser, String url);

    /**
     * 修改邮箱
     * @param jwtUser
     * @param email
     */
    void updateEmail(JwtUser jwtUser, String email);
}
