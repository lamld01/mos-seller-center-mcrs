package com.lamld.ossellercentermcrs.domain.services.product;

import com.lamld.ossellercentermcrs.app.dto.product.CreateUpdateProductRequest;
import com.lamld.ossellercentermcrs.app.response.product.ProductResponse;
import com.lamld.ossellercentermcrs.domain.entities.product.ProductEntity;
import com.lamld.ossellercentermcrs.domain.shareService.product.CategoryShareService;
import com.lamld.ossellercentermcrs.domain.shareService.product.ProductShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.CurrencyUnit;

@Service
@RequiredArgsConstructor
public class ProductService extends ProductShareService {

  private final CategoryShareService categoryShareService;

  public Page<ProductResponse> getPage(Long groupId, String name, Long categoryId, ActiveStatus status, Pageable pageable) {
    Long userId = getRequestUserId();
    Page<ProductEntity> productEntities = getPageProduct(userId, groupId, name, categoryId, status, pageable);
    return mapperUtil.mapPage(productEntities, ProductResponse.class);
  }

  public ProductResponse createProduct(CreateUpdateProductRequest request) {

    ProductEntity product = new ProductEntity();
    return updateProductInfo(request, product);
  }

  public ProductResponse updateProduct(Long id, CreateUpdateProductRequest request) {

    ProductEntity product = getExistProductByIdNoCache(id);
    return updateProductInfo(request, product);
  }

  private ProductResponse updateProductInfo(CreateUpdateProductRequest request, ProductEntity product) {
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStatus(request.getStatus());
    product.setCurrency(CurrencyUnit.VND);
    product = save(product);
    return new ProductResponse(product);
  }
}
