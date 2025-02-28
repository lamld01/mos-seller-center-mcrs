package com.lamld.ossellercentermcrs.app.dto.auth;

import lombok.Getter;
import lombok.Setter;
import vn.mos.core.base.type.UserRole;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private UserRole userRole;
    private String password;
    private String displayName;
    private String phoneNumber;
    private String email;
}
