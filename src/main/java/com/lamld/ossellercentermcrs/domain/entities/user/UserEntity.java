package com.lamld.ossellercentermcrs.domain.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "account_id", nullable = false, unique = true)
  private Long accountId;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "email")
  private String email;
}
