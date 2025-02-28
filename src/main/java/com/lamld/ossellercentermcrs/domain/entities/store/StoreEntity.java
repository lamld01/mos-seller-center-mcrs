package com.lamld.ossellercentermcrs.domain.entities.store;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.StoreStatus;

@Getter
@Setter
@Entity
@Table(name = "store")
public class StoreEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreStatus status;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;
}