package com.micb2b.purchasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.micb2b.purchasing.domain.Role;
import com.micb2b.purchasing.exception.BadRequestException;
import com.micb2b.purchasing.service.RoleService;
import com.micb2b.purchasing.service.query.RoleQueryService;

/**
 * @author jie
 * @date 2018-12-03
 */
@RestController
@RequestMapping("api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleQueryService roleQueryService;

    private static final String ENTITY_NAME = "role";

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity getRoles(@PathVariable Long id){
        return new ResponseEntity(roleService.findById(id), HttpStatus.OK);
    }

    /**
     * 返回全部的角色，新增用戶時下拉選擇
     * @return
     */
    @GetMapping(value = "/roles/tree")
    public ResponseEntity getRoleTree(){
        return new ResponseEntity(roleService.getRoleTree(),HttpStatus.OK);
    }

    @Log("角色管理-查詢角色")
    @GetMapping(value = "/roles")
    public ResponseEntity getRoles(@RequestParam(required = false) String name,  Pageable pageable){
        return new ResponseEntity(roleQueryService.queryAll(name,pageable),HttpStatus.OK);
    }

    @Log("角色管理-新增角色")
    @PostMapping(value = "/roles")
    public ResponseEntity create(@Validated @RequestBody Role resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(roleService.create(resources),HttpStatus.CREATED);
    }

    @Log("角色管理-修改角色")
    @PutMapping(value = "/roles")
    public ResponseEntity update(@Validated(Role.Update.class) @RequestBody Role resources){
        roleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("角色管理-修改角色許可權")
    @PutMapping(value = "/roles/permission")
    public ResponseEntity updatePermission(@RequestBody Role resources){
        roleService.updatePermission(resources,roleService.findById(resources.getId()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("角色管理-修改角色選單")
    @PutMapping(value = "/roles/menu")
    public ResponseEntity updateMenu(@RequestBody Role resources){
        roleService.updateMenu(resources,roleService.findById(resources.getId()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("角色管理-刪除角色")
    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        roleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
