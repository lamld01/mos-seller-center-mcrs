package com.lamld.ossellercentermcrs.app.response.store;

import com.lamld.ossellercentermcrs.domain.entities.store.StoreBranchEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mos.core.base.type.StoreBranchStatus;

@Data
@NoArgsConstructor
public class StoreBranchResponse {
  private Long id;
  private String name;
  private String address;
  private String phoneNumber;
  private StoreBranchStatus status;

  public StoreBranchResponse(StoreBranchEntity storeBranchEntity) {
    this.id = storeBranchEntity.getId();
    this.name = storeBranchEntity.getName();
    this.address = storeBranchEntity.getAddress();
    this.phoneNumber = storeBranchEntity.getPhoneNumber();
    this.status = storeBranchEntity.getStatus();
  }
}
