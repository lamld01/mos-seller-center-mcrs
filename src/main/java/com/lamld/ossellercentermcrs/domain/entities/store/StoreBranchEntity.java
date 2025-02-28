package com.lamld.ossellercentermcrs.domain.entities.store;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.StoreBranchStatus;

@Getter
@Setter
@Entity
@Table(name = "store_branch")
public class StoreBranchEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "store_id", nullable = false)
  private Long storeId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreBranchStatus status;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;
}