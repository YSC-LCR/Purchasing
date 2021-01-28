package com.micb2b.purchasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.micb2b.purchasing.domain.BackendProdUnitInfo;

/**
 * @author JayYang
 */
public interface BackendProdUnitInfoRepository extends JpaRepository<BackendProdUnitInfo, Long>, JpaSpecificationExecutor {

	@Query(value = " select * from backend_prod_unit_info where unit_id=?1 order by unit_info_id desc ", nativeQuery = true)
	List<BackendProdUnitInfo> findAllByUnit(Long unitId);

}
