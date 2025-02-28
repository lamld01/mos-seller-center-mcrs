package com.lamld.ossellercentermcrs.domain.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.AccountStatus;
import vn.mos.core.base.type.UserRole;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "account_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountStatus accountStatus;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

}