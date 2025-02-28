package com.lamld.ossellercentermcrs.domain.entities.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.CurrencyUnit;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "product_group_id", nullable = false)
  private Long productGroupId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "currency", nullable = false)
  private CurrencyUnit currency;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ActiveStatus status;

}