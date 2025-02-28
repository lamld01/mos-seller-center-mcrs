package com.lamld.mossellermcrs.domain.entities.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.ProductCategoryType;

@Getter
@Setter
@Entity
@Table(name = "category_entity")
public class CategoryEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "description", nullable = true, length = 255)
  private String description;

  @Column(name = "parent_id")
  private Long parentId;

  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  private ProductCategoryType type;
}