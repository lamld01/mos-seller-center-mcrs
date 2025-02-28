package com.lamld.ossellercentermcrs.app.controllers.product;

import com.lamld.ossellercentermcrs.app.dto.product.CreateUpdateProductRequest;
import com.lamld.ossellercentermcrs.app.response.product.ProductResponse;
import com.lamld.ossellercentermcrs.domain.services.product.ProductService;
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
@RequestMapping("/v1/seller-center/products")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class ProductController {

  private final ProductService productService;

  @GetMapping("page")
  @PageableAsQueryParam
  public BaseResponse<PageResponse<ProductResponse>> getPageProducts(
      @RequestParam(required = false) Long groupId,
      @RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) Long categoryId,
                                                                     @RequestParam(required = false) ActiveStatus status,
                                                                     @Parameter(hidden = true) Pageable pageable
  ) {
    return BaseResponse.success(PageResponse.fromPage(productService.getPage(groupId, name, categoryId, status, pageable)));
  }

  @PostMapping("create")
  public BaseResponse<ProductResponse> createProduct(@RequestBody CreateUpdateProductRequest request) {
    return BaseResponse.success(productService.createProduct(request));
  }

  @PutMapping("{id}/update")
  public BaseResponse<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody CreateUpdateProductRequest request) {
    return BaseResponse.success(productService.updateProduct(id, request));
  }
}
