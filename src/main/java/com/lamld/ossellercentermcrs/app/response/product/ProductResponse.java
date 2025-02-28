package com.lamld.ossellercentermcrs.app.response.product;

import com.lamld.ossellercentermcrs.domain.entities.product.ProductEntity;
import lombok.Data;
import vn.mos.core.base.type.ActiveStatus;

import java.time.LocalDateTime;

@Data
public class ProductResponse {

  private Long id;

  private String name;

  private Long productGroupId;

  private String description;

  private Double price;

  private ActiveStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public ProductResponse(ProductEntity productEntity){
    this.id = productEntity.getId();
    this.productGroupId = productEntity.getProductGroupId();
    this.name = productEntity.getName();
    this.description = productEntity.getDescription();
    this.price = productEntity.getPrice();
    this.status = productEntity.getStatus();
    this.createdAt = productEntity.getCreatedAt();
    this.updatedAt = productEntity.getUpdatedAt();
  }
}


