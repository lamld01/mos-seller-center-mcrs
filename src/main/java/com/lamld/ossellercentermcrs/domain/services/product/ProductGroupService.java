package com.lamld.ossellercentermcrs.domain.services.product;

import com.lamld.ossellercentermcrs.app.dto.product.CreateUpdateProductGroupRequest;
import com.lamld.ossellercentermcrs.app.response.product.ProductGroupResponse;
import com.lamld.ossellercentermcrs.domain.entities.product.ProductGroupEntity;
import com.lamld.ossellercentermcrs.domain.shareService.product.CategoryShareService;
import com.lamld.ossellercentermcrs.domain.shareService.product.ProductGroupShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.CurrencyUnit;

@Service
@RequiredArgsConstructor
public class ProductGroupService extends ProductGroupShareService {

  private final CategoryShareService categoryShareService;

  public Page<ProductGroupResponse> getPage(String name, Long categoryId, ActiveStatus status, Pageable pageable) {
    Long userId = getRequestUserId();
    Page<ProductGroupEntity> productGroupEntities = getPageProductGroup(userId, name, categoryId, status, pageable);
    return mapperUtil.mapPage(productGroupEntities, ProductGroupResponse.class);
  }

  public ProductGroupResponse createProductGroup(CreateUpdateProductGroupRequest request) {
    categoryShareService.getExistCategoryById(request.getCategoryId());

    ProductGroupEntity productGroup = new ProductGroupEntity();
    return updateProductGroupInfo(request, productGroup);
  }

  public ProductGroupResponse updateProductGroup(Long id, CreateUpdateProductGroupRequest request) {
    categoryShareService.getExistCategoryById(request.getCategoryId());

    ProductGroupEntity productGroup = getExistProductGroupByIdNoCache(id);
    return updateProductGroupInfo(request, productGroup);
  }

  private ProductGroupResponse updateProductGroupInfo(CreateUpdateProductGroupRequest request, ProductGroupEntity productGroup) {
    productGroup.setName(request.getName());
    productGroup.setCategoryId(request.getCategoryId());
    productGroup.setDescription(request.getDescription());
    productGroup.setStatus(request.getStatus());
    productGroup = save(productGroup);
    return new ProductGroupResponse(productGroup);
  }
}
