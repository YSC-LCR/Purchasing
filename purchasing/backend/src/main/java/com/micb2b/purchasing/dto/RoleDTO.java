package com.micb2b.purchasing.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import lombok.Data;

/**
 * @author jay
 * @date 2019-11-23
 */
@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private Set<PermissionDTO> permissions;

    private Set<MenuDTO> menus;

    private Timestamp createTime;
}
