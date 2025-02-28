package com.lamld.ossellercentermcrs.app.controllers.store;

import com.lamld.ossellercentermcrs.app.dto.store.CreateStoreBranchRequest;
import com.lamld.ossellercentermcrs.app.dto.store.UpdateStoreBranchRequest;
import com.lamld.ossellercentermcrs.app.response.store.StoreBranchResponse;
import com.lamld.ossellercentermcrs.domain.services.store.StoreBranchService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.mos.core.base.BaseResponse;
import vn.mos.core.base.type.PageResponse;
import vn.mos.core.base.type.StoreBranchStatus;

@RestController
@RequestMapping("/v1/seller-center/store-branches")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class StoreBranchController {

  private final StoreBranchService storeBranchService;

  @PostMapping("/create")
  public BaseResponse<StoreBranchResponse> createSellerStore(@RequestBody CreateStoreBranchRequest request) {
    return BaseResponse.success(storeBranchService.createStoreBranch(request));
  }

  @PutMapping("{id}/update")
  public BaseResponse<StoreBranchResponse> updateSellerStore(@PathVariable Long id,
                                                             @RequestBody UpdateStoreBranchRequest request) {
    return BaseResponse.success(storeBranchService.updateStoreBranch(id, request));
  }


  @GetMapping("/page")
  @PageableAsQueryParam
  public BaseResponse<PageResponse<StoreBranchResponse>> getPageStore(
      @RequestParam Long storeId,
      @RequestParam(required = false) String phoneNumber,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) StoreBranchStatus status,
      @Parameter(hidden = true) Pageable pageable) {
    return BaseResponse.success(PageResponse.fromPage(storeBranchService.getPageStoreBranches(storeId, phoneNumber, name, status, pageable)));
  }

  @GetMapping("/{id}/detail")
  public BaseResponse<StoreBranchResponse> getStoreDetail(@PathVariable Long id) {
    return BaseResponse.success(storeBranchService.getStoreBranchDetail(id));
  }

}
