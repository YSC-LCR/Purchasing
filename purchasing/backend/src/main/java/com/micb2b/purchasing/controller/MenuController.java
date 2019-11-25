package com.micb2b.purchasing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micb2b.purchasing.aop.Log;
import com.micb2b.purchasing.domain.Menu;
import com.micb2b.purchasing.domain.User;
import com.micb2b.purchasing.dto.MenuDTO;
import com.micb2b.purchasing.exception.BadRequestException;
import com.micb2b.purchasing.service.MenuService;
import com.micb2b.purchasing.service.RoleService;
import com.micb2b.purchasing.service.UserService;
import com.micb2b.purchasing.service.query.MenuQueryService;
import com.micb2b.purchasing.utils.SecurityContextHolder;

/**
 * @author jie
 * @date 2018-12-03
 */
@RestController
@RequestMapping("api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuQueryService menuQueryService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private static final String ENTITY_NAME = "menu";

    /**
     * 構建前端路由所需要的功能表
     * @return
     */
    @GetMapping(value = "/menus/build")
    public ResponseEntity buildMenus(){
        UserDetails userDetails = SecurityContextHolder.getUserDetails();
        User user = userService.findByName(userDetails.getUsername());
        List<MenuDTO> menuDTOList = menuService.findByRoles(roleService.findByUsers_Id(user.getId()));
        List<MenuDTO> menuDTOTree = (List<MenuDTO>)menuService.buildTree(menuDTOList).get("content");
        return new ResponseEntity(menuService.buildMenus(menuDTOTree),HttpStatus.OK);
    }

    /**
     * 返回全部的功能表
     * @return
     */
    @GetMapping(value = "/menus/tree")
    public ResponseEntity getMenuTree(){
        return new ResponseEntity(menuService.getMenuTree(menuService.findByPid(0L)),HttpStatus.OK);
    }

    @Log("功能表管理-查詢選單")
    @GetMapping(value = "/menus")
    public ResponseEntity getMenus(@RequestParam(required = false) String name, Sort sort){
        List<MenuDTO> menuDTOList = menuQueryService.queryAll(name, sort);
        return new ResponseEntity(menuService.buildTree(menuDTOList),HttpStatus.OK);
    }

    @Log("功能表管理-新增選單")
    @PostMapping(value = "/menus")
    public ResponseEntity create(@Validated @RequestBody Menu resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(menuService.create(resources),HttpStatus.CREATED);
    }

    @Log("功能表管理-修改選單")
    @PutMapping(value = "/menus")
    public ResponseEntity update(@Validated(Menu.Update.class) @RequestBody Menu resources){
        menuService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("功能表管理-刪除選單")
    @DeleteMapping(value = "/menus/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        menuService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
