package com.lamld.ossellercentermcrs.app.controllers.store;

import com.lamld.ossellercentermcrs.app.dto.store.CreateStoreRequest;
import com.lamld.ossellercentermcrs.app.dto.store.UpdateStoreRequest;
import com.lamld.ossellercentermcrs.app.response.store.StoreResponse;
import com.lamld.ossellercentermcrs.domain.services.store.StoreService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.mos.core.base.BaseResponse;
import vn.mos.core.base.type.PageResponse;

@RestController
@RequestMapping("/v1/seller-center/stores")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create")
    public BaseResponse<StoreResponse> createSellerStore(@RequestBody CreateStoreRequest request) {
        return BaseResponse.success(storeService.createStore(request));
    }

    @PutMapping("{id}/update")
    public BaseResponse<StoreResponse> updateSellerStore(@PathVariable Long id,
                                           @RequestBody UpdateStoreRequest request) {
        return BaseResponse.success(storeService.updateStore(id, request));
    }


    @GetMapping("/page")
    @PageableAsQueryParam
    public BaseResponse<PageResponse<StoreResponse>> getPageStore(@RequestParam(required = false) String phoneNumber,
                                     @RequestParam(required = false) String name,
                                     @Parameter(hidden = true) Pageable pageable) {
        return BaseResponse.success(PageResponse.fromPage(storeService.getPageStore(phoneNumber, name, pageable)));
    }

    @GetMapping("/{id}/detail")
    public BaseResponse<StoreResponse> getStoreDetail(@PathVariable Long id) {
        return BaseResponse.success(storeService.getStoreDetail(id));
    }

}
