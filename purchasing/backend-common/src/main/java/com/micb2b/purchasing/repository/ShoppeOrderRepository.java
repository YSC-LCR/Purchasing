package com.micb2b.purchasing.repository;

import com.micb2b.purchasing.domain.ShopeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author JayYang
 */
public interface ShoppeOrderRepository extends JpaRepository<ShopeeOrder, Long>, JpaSpecificationExecutor {

}
