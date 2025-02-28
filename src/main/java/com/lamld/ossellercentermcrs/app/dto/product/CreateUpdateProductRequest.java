package com.lamld.ossellercentermcrs.app.dto.product;

import lombok.Data;
import vn.mos.core.base.type.ActiveStatus;

@Data
public class CreateUpdateProductRequest {

  private Long productGroupId;

  private String name;

  private String description;

  private Double price;

  private ActiveStatus status;

}


