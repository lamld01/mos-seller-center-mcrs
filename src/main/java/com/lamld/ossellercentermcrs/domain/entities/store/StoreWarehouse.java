package com.lamld.ossellercentermcrs.domain.entities.store;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.CurrencyUnit;

@Getter
@Setter
@Entity
@Table(name = "store_warehouse")
public class StoreWarehouse extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "store_branch_id", nullable = false)
  private Long storeBranchId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "quantity", nullable = false)
  private Long quantity;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "currency_unit", nullable = false)
  @Enumerated(EnumType.STRING)
  private CurrencyUnit currencyUnit;
}
