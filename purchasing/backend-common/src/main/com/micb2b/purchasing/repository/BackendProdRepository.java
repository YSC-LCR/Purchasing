package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.micb2b.purchasing.domain.BackendProd;

/**
 * @author JayYang
 */
public interface BackendProdRepository extends JpaRepository<BackendProd, Long>, JpaSpecificationExecutor {

	@Modifying
	@Query(value = " update backend_prod set delete_flag = 'Y',update_date = now(),update_by=?2 where prod_id = ?1 ", nativeQuery = true)
	void updateDeleteFlagByProdId(Long prodId,String updateBy);
}
