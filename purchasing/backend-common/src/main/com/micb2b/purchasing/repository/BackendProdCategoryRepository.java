package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.micb2b.purchasing.domain.BackendProdCategory;

/**
 * @author JayYang
 */
public interface BackendProdCategoryRepository extends JpaRepository<BackendProdCategory, Long>, JpaSpecificationExecutor {
	@Modifying
	@Query(value = " update backend_prod_category set delete_flag = 'Y',update_date = now(),update_by=?2 where category_id = ?1 ", nativeQuery = true)
	void updateDeleteFlagByCategoryId(Long categoryId,String updateBy);	
}
