package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.micb2b.purchasing.domain.BackendProdImages;

/**
 * @author JayYang
 */
public interface BackendProdImagesRepository extends JpaRepository<BackendProdImages, Long>, JpaSpecificationExecutor {

}
