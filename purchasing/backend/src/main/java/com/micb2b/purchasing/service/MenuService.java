package com.micb2b.purchasing.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.micb2b.purchasing.dto.MenuDTO;
import com.micb2b.purchasing.domain.Menu;
import com.micb2b.purchasing.domain.Role;

/**
 * @author jay
 * @date 2018-12-17
 */
public interface MenuService {

    /**
     * get
     * @param id
     * @return
     */
    MenuDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    MenuDTO create(Menu resources);

    /**
     * update
     * @param resources
     */
    void update(Menu resources);

    /**
     * delete
     * @param id
     */
    void delete(Long id);

    /**
     * permission tree
     * @return
     */
    Object getMenuTree(List<Menu> menus);

    /**
     * findByPid
     * @param pid
     * @return
     */
    List<Menu> findByPid(long pid);

    /**
     * build Tree
     * @param menuDTOS
     * @return
     */
    Map buildTree(List<MenuDTO> menuDTOS);

    /**
     * findByRoles
     * @param roles
     * @return
     */
    List<MenuDTO> findByRoles(Set<Role> roles);

    /**
     * buildMenus
     * @param byRoles
     * @return
     */
    Object buildMenus(List<MenuDTO> byRoles);
}
