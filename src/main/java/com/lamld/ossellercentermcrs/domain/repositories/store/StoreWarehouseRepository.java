package com.lamld.ossellercentermcrs.domain.repositories.store;

import com.lamld.ossellercentermcrs.domain.entities.store.StoreWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreWarehouseRepository extends JpaRepository<StoreWarehouse, Long>, JpaSpecificationExecutor<StoreWarehouse> {
}