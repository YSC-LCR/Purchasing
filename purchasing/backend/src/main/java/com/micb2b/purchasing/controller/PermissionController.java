package com.micb2b.purchasing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.micb2b.purchasing.domain.Permission;
import com.micb2b.purchasing.dto.PermissionDTO;
import com.micb2b.purchasing.exception.BadRequestException;
import com.micb2b.purchasing.service.PermissionService;
import com.micb2b.purchasing.service.query.PermissionQueryService;

/**
 * @author jie
 * @date 2018-12-03
 */
@RestController
@RequestMapping("api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionQueryService permissionQueryService;

    private static final String ENTITY_NAME = "permission";

    /**
     * 返回全部的許可權，新增角色時下拉選擇
     * @return
     */
    @GetMapping(value = "/permissions/tree")
    public ResponseEntity getRoleTree(){
        return new ResponseEntity(permissionService.getPermissionTree(permissionService.findByPid(0L)),HttpStatus.OK);
    }

    @Log("許可權管理-查詢許可權")
    @GetMapping(value = "/permissions")
    public ResponseEntity getPermissions(@RequestParam(required = false) String name){
        List<PermissionDTO> permissionDTOS = permissionQueryService.queryAll(name);
        return new ResponseEntity(permissionService.buildTree(permissionDTOS),HttpStatus.OK);
    }

    @Log("許可權管理-新增許可權")
    @PostMapping(value = "/permissions")
    public ResponseEntity create(@Validated @RequestBody Permission resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(permissionService.create(resources),HttpStatus.CREATED);
    }

    @Log("許可權管理-修改許可權")
    @PutMapping(value = "/permissions")
    public ResponseEntity update(@Validated(Permission.Update.class) @RequestBody Permission resources){
        permissionService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("許可權管理-刪除許可權")
    @DeleteMapping(value = "/permissions/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        permissionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
