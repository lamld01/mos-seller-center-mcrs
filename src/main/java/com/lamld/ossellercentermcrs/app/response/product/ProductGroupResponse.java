package com.lamld.ossellercentermcrs.app.response.product;

import com.lamld.ossellercentermcrs.domain.entities.product.ProductEntity;
import com.lamld.ossellercentermcrs.domain.entities.product.ProductGroupEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mos.core.base.type.ActiveStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductGroupResponse {

  private Long id;

  private Long categoryId;

  private String name;

  private String description;

  private ActiveStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public ProductGroupResponse(ProductGroupEntity productEntity){
    this.id = productEntity.getId();
    this.categoryId = productEntity.getCategoryId();
    this.name = productEntity.getName();
    this.description = productEntity.getDescription();
    this.status = productEntity.getStatus();
    this.createdAt = productEntity.getCreatedAt();
    this.updatedAt = productEntity.getUpdatedAt();
  }
}


