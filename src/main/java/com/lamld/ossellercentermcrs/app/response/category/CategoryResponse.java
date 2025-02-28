package com.lamld.ossellercentermcrs.app.response.category;

import jakarta.persistence.*;
import lombok.Data;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.ProductCategoryType;

@Data
public class CategoryResponse {

  private Long id;

  private String name;

  private String description;

  private Long parentId;

  private ActiveStatus status;

  private ProductCategoryType type;
}
