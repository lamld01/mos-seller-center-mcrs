package com.lamld.ossellercentermcrs.domain.services.user;

import com.lamld.ossellercentermcrs.app.dto.auth.AuthRequest;
import com.lamld.ossellercentermcrs.app.dto.auth.RegisterRequest;
import com.lamld.ossellercentermcrs.app.response.auth.AuthResponse;
import com.lamld.ossellercentermcrs.domain.entities.user.AccountEntity;
import com.lamld.ossellercentermcrs.domain.shareService.user.AccountShareService;
import com.lamld.ossellercentermcrs.domain.shareService.user.UserShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.mos.core.base.type.AccountStatus;
import vn.mos.core.base.type.BusinessErrorCode;
import vn.mos.core.exceptions.BusinessException;
import vn.mos.core.sercurities.data.TokenUserInfo;
import vn.mos.core.sercurities.service.PasswordAuthenticationService;
import vn.mos.core.utils.JwtUtil;

@Service
@RequiredArgsConstructor
public class AccountService extends AccountShareService {
    private final UserShareService userShareService;
    private final PasswordAuthenticationService passwordAuthenticationService;
    private final JwtUtil jwtUtil;

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public AuthResponse register(RegisterRequest request) {
        AccountEntity account = new AccountEntity();
        account.setUsername(request.getUsername());
        account.setPassword(passwordAuthenticationService.encodePassword(request.getPassword()));
        account.setUserRole(request.getUserRole());
        account.setAccountStatus(AccountStatus.ACTIVATED);

        account = saveUserAccount(account);
        userShareService.createUser(request, account);
        String token = jwtUtil.generateToken(TokenUserInfo
            .builder()
            .userId(account.getId())
            .username(account.getUsername())
            .userRole(account.getUserRole())
            .build());
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        AccountEntity account = getExistUserAccountByUsername(request.getUsername());
        if(passwordAuthenticationService.checkPassword(account.getPassword(), request.getPassword())) {
            throw new BusinessException(BusinessErrorCode.BAD_REQUEST, "Username or Password not valid");
        }
        String token = jwtUtil.generateToken(TokenUserInfo
            .builder()
            .userId(account.getId())
            .username(account.getUsername())
            .userRole(account.getUserRole())
            .build());
        return new AuthResponse(token);
    }
}
