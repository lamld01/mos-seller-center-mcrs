package com.lamld.ossellercentermcrs.app.controllers.user;

import com.lamld.ossellercentermcrs.app.response.user.UserResponse;
import com.lamld.ossellercentermcrs.domain.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.mos.core.base.BaseResponse;

@RestController
@RequestMapping("/v1/seller-center/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public BaseResponse<UserResponse> getUserInfo() {
        return BaseResponse.success(userService.getUserInfo());
    }

}
