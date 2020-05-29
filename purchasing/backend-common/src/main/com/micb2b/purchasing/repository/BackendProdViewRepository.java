package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.micb2b.purchasing.domain.BackendProdView;

/**
 * @author JayYang
 */
public interface BackendProdViewRepository extends JpaRepository<BackendProdView, String>, JpaSpecificationExecutor {
}
