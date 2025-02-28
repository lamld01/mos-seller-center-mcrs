package com.lamld.ossellercentermcrs.app.dto.product;

import lombok.Data;
import vn.mos.core.base.type.ActiveStatus;

@Data
public class CreateUpdateProductGroupRequest {

  private Long categoryId;

  private String name;

  private String description;

  private ActiveStatus status;

}


