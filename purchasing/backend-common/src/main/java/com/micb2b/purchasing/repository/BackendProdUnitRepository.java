package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.micb2b.purchasing.domain.BackendProdUnit;

/**
 * @author JayYang
 */
public interface BackendProdUnitRepository extends JpaRepository<BackendProdUnit, Long>, JpaSpecificationExecutor {
	@Modifying
	@Query(value = " update backend_prod_unit set delete_flag = 'Y',update_date = now(),update_by=?2 where unit_id = ?1 ", nativeQuery = true)
	void updateDeleteFlagByUnitId(Long unitId,String updateBy);	
}
