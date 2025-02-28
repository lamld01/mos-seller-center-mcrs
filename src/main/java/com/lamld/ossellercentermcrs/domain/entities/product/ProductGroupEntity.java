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
@Table(name = "product_group")
public class ProductGroupEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ActiveStatus status;

}