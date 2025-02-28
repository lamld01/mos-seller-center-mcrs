package com.lamld.ossellercentermcrs.app.response.user;

import com.lamld.ossellercentermcrs.domain.entities.user.AccountEntity;
import com.lamld.ossellercentermcrs.domain.entities.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mos.core.base.type.AccountStatus;
import vn.mos.core.base.type.UserRole;

@Data
@NoArgsConstructor
public class UserResponse {
  private Long id;
  private String username;
  private String displayName;
  private String email;
  private String phoneNumber;
  private UserRole userRole;
  private AccountStatus accountStatus;

  public UserResponse(UserEntity userRole, AccountEntity account) {
    this.id = userRole.getId();
    this.username = account.getUsername();
    this.displayName = userRole.getDisplayName();
    this.email = userRole.getEmail();
    this.phoneNumber = userRole.getPhoneNumber();
    this.userRole = account.getUserRole();
    this.accountStatus = account.getAccountStatus();

  }
}
