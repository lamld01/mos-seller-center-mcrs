package com.lamld.ossellercentermcrs.domain.services.user;

import com.lamld.ossellercentermcrs.app.response.user.UserResponse;
import com.lamld.ossellercentermcrs.domain.entities.user.AccountEntity;
import com.lamld.ossellercentermcrs.domain.entities.user.UserEntity;
import com.lamld.ossellercentermcrs.domain.shareService.user.AccountShareService;
import com.lamld.ossellercentermcrs.domain.shareService.user.UserShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends UserShareService {

    private final AccountShareService accountShareService;

    public UserResponse getUserInfo() {
        Long userId = getRequestUserId();
        UserEntity user = getExistUserById(userId);
        AccountEntity account = accountShareService.getExistUserAccountById(user.getAccountId());
        return new UserResponse(user, account);
    }
}
