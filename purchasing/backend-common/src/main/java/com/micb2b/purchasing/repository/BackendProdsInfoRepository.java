package com.micb2b.purchasing.repository;

import com.micb2b.purchasing.domain.BackendProdsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BackendProdsInfoRepository extends JpaRepository<BackendProdsInfo, Long>, JpaSpecificationExecutor {
}
