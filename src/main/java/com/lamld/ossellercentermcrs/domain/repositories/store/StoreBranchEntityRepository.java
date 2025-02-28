package com.lamld.ossellercentermcrs.domain.repositories.store;

import com.lamld.ossellercentermcrs.domain.entities.store.StoreBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreBranchEntityRepository extends JpaRepository<StoreBranchEntity, Long>, JpaSpecificationExecutor<StoreBranchEntity> {
}