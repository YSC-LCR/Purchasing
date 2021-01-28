package com.micb2b.purchasing.repository;

import com.micb2b.purchasing.domain.BackendProdSubCategoryProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author JayYang
 */
public interface BackendProdSubCategoryProdRepository extends JpaRepository<BackendProdSubCategoryProd, Long>, JpaSpecificationExecutor {

}
