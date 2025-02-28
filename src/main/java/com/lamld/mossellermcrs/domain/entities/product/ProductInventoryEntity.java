package com.lamld.mossellermcrs.domain.entities.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "product_inventory_entity")
public class ProductInventoryEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "branch_id", nullable = false)
  private Long branchId;

  @Column(name = "stock", nullable = false)
  private Integer stock;

}