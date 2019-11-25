package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.micb2b.purchasing.domain.SysParam;


/**
 * @author jayyang
 */
public interface SysParamRepository extends JpaRepository<SysParam, String>, JpaSpecificationExecutor {

}
