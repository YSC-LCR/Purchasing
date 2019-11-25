package com.micb2b.purchasing.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.micb2b.purchasing.domain.Permission;

/**
 * @author jie
 * @date 2019-11-19
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor {

    /**
     * findByName
     * @param name
     * @return
     */
    Permission findByName(String name);

    /**
     * findByPid
     * @param pid
     * @return
     */
    List<Permission> findByPid(long pid);

    Set<Permission> findByRoles_Id(Long id);

    /**
     * �閰Ｚ��I雿輻甈��
     * @param username
     */
    @Query(value = "select p.name " + 
    		"from user u, users_roles ur, role r, roles_permissions rp, permission p " + 
    		"where u.id = ur.user_id " + 
    		"and ur.role_id = r.id " + 
    		"and ur.role_id = rp.role_id " + 
    		"and p.id = rp.permission_id " + 
    		"and u.username = ?1 " + 
    		"and p.pid <> '0' " +
    		"and p.name like 'MENU%' " + 
    		"union " + 
    		"select re.event_id " + 
    		"from user u, users_roles ur, roles_events re " + 
    		"where u.id = ur.user_id " + 
    		"and ur.role_id = re.role_id " + 
    		"and u.username = ?1",nativeQuery = true)
    List<String> getRewardUiPermissions(String username);
}
