package com.lamld.ossellercentermcrs.domain.repositories.product;

import com.lamld.ossellercentermcrs.domain.entities.product.ProductEntity;
import com.lamld.ossellercentermcrs.domain.entities.product.ProductGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductGroupEntityRepository extends JpaRepository<ProductGroupEntity, Long>, JpaSpecificationExecutor<ProductGroupEntity> {
}