package com.lamld.ossellercentermcrs.app.controllers.product;

import com.lamld.ossellercentermcrs.app.dto.product.CreateUpdateProductGroupRequest;
import com.lamld.ossellercentermcrs.app.response.product.ProductGroupResponse;
import com.lamld.ossellercentermcrs.domain.services.product.ProductGroupService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.mos.core.base.BaseResponse;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.PageResponse;

@RestController
@RequestMapping("/v1/seller-center/product-group")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class ProductGroupController {

  private final ProductGroupService productGroupService;

  @GetMapping("page")
  @PageableAsQueryParam
  public BaseResponse<PageResponse<ProductGroupResponse>> getPageProductGroups(@RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) Long categoryId,
                                                                     @RequestParam(required = false) ActiveStatus status,
                                                                     @Parameter(hidden = true) Pageable pageable
  ) {
    return BaseResponse.success(PageResponse.fromPage(productGroupService.getPage(name, categoryId, status, pageable)));
  }

  @PostMapping("create")
  public BaseResponse<ProductGroupResponse> createProductGroup(@RequestBody CreateUpdateProductGroupRequest request) {
    return BaseResponse.success(productGroupService.createProductGroup(request));
  }

  @PutMapping("{id}/update")
  public BaseResponse<ProductGroupResponse> updateProductGroup(@PathVariable Long id, @RequestBody CreateUpdateProductGroupRequest request) {
    return BaseResponse.success(productGroupService.updateProductGroup(id, request));
  }
}
