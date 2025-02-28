package com.lamld.ossellercentermcrs.domain.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.BaseEntity;
import vn.mos.core.base.type.CurrencyUnit;

@Getter
@Setter
@Entity
@Table(name = "wallet")
public class WalletEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "balance", nullable = false)
  private Double balance;

  @Column(name = "currency_unit", nullable = false)
  @Enumerated(EnumType.STRING)
  private CurrencyUnit currencyUnit;
}
