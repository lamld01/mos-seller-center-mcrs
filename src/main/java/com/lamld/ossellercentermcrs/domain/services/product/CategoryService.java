package com.lamld.ossellercentermcrs.domain.services.product;

import com.lamld.ossellercentermcrs.app.response.category.CategoryResponse;
import com.lamld.ossellercentermcrs.domain.entities.product.CategoryEntity;
import com.lamld.ossellercentermcrs.domain.shareService.product.CategoryShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.ProductCategoryType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService extends CategoryShareService {

  public List<CategoryResponse> findAllCategories(Long parentId, String name, ActiveStatus status, ProductCategoryType type) {
    List<CategoryEntity> categories = getAllCategories(parentId, name, status, type);
    return mapperUtil.mapList(categories, CategoryResponse.class);
  }
}
