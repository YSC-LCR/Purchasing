package com.micb2b.purchasing.repository;

import com.micb2b.purchasing.domain.BackendProdSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author JayYang
 */
public interface BackendProdSubCategoryRepository extends JpaRepository<BackendProdSubCategory, Long>, JpaSpecificationExecutor {

	@Query(value = " select * from backend_prod_sub_category where category_id=?1 and delete_flag = 'N' order by sub_category_id desc ", nativeQuery = true)
	List<BackendProdSubCategory> findAllByCategory(Long subCategoryId);
	
	@Modifying
	@Query(value = " update backend_prod_sub_category set delete_flag = 'Y',update_date = now(),update_by = ?2 where sub_category_id = ?1 ", nativeQuery = true)
	void updateDeleteFlagBySubCategoryId(Long subCategoryId,String updateBy);	
}
