package com.lamld.ossellercentermcrs.app.response.store;

import com.lamld.ossellercentermcrs.domain.entities.store.StoreEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mos.core.base.type.StoreStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StoreResponse {

  private Long id;

  private String phoneNumber;

  private String name;

  private StoreStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public StoreResponse(StoreEntity storeEntity){
    this.id = storeEntity.getId();
    this.phoneNumber = storeEntity.getPhoneNumber();
    this.name = storeEntity.getName();
    this.status = storeEntity.getStatus();
    this.createdAt = storeEntity.getCreatedAt();
    this.updatedAt = storeEntity.getUpdatedAt();
  }
}
