package com.micb2b.purchasing.service;

import java.util.List;

import com.micb2b.purchasing.domain.Permission;
import com.micb2b.purchasing.dto.PermissionDTO;

/**
 * @author jie
 * @date 2018-12-08
 */
public interface PermissionService {

    /**
     * get
     * @param id
     * @return
     */
    PermissionDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    PermissionDTO create(Permission resources);

    /**
     * update
     * @param resources
     */
    void update(Permission resources);

    /**
     * delete
     * @param id
     */
    void delete(Long id);

    /**
     * permission tree
     * @return
     */
    Object getPermissionTree(List<Permission> permissions);

    /**
     * findByPid
     * @param pid
     * @return
     */
    List<Permission> findByPid(long pid);

    /**
     * build Tree
     * @param permissionDTOS
     * @return
     */
    Object buildTree(List<PermissionDTO> permissionDTOS);
}
