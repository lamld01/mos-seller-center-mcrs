package com.lamld.ossellercentermcrs.app.controllers.product;

import com.lamld.ossellercentermcrs.app.response.category.CategoryResponse;
import com.lamld.ossellercentermcrs.domain.services.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.mos.core.base.BaseResponse;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.ProductCategoryType;

import java.util.List;

@RestController
@RequestMapping("/v1/seller-center/categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("all")
  public BaseResponse<List<CategoryResponse>> findAllCategories(@RequestParam(required = false) Long parentId,
                                                                @RequestParam(required = false) String name,
                                                                @RequestParam(required = false) ProductCategoryType type,
                                                                @RequestParam(required = false) ActiveStatus status) {
    return BaseResponse.success(categoryService.findAllCategories(parentId, name, status, type));
  }
}
