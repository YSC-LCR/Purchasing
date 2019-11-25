package com.micb2b.purchasing.service;

import java.util.Set;

import com.micb2b.purchasing.domain.Role;
import com.micb2b.purchasing.dto.RoleDTO;

/**
 * @author jie
 * @date 2018-12-03
 */
public interface RoleService {

    /**
     * get
     * @param id
     * @return
     */
    RoleDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    RoleDTO create(Role resources);

    /**
     * update
     * @param resources
     */
    void update(Role resources);

    /**
     * delete
     * @param id
     */
    void delete(Long id);

    /**
     * role tree
     * @return
     */
    Object getRoleTree();

    /**
     * findByUsers_Id
     * @param id
     * @return
     */
    Set<Role> findByUsers_Id(Long id);

    /**
     * updatePermission
     * @param resources
     * @param roleDTO
     */
    void updatePermission(Role resources, RoleDTO roleDTO);

    /**
     * updateMenu
     * @param resources
     * @param roleDTO
     */
    void updateMenu(Role resources, RoleDTO roleDTO);
}
