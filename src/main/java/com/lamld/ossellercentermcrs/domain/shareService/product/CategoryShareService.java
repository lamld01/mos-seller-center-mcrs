package com.lamld.ossellercentermcrs.domain.shareService.product;

import com.lamld.ossellercentermcrs.domain.entities.product.CategoryEntity;
import com.lamld.ossellercentermcrs.domain.repositories.product.CategoryEntityRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.BusinessErrorCode;
import vn.mos.core.base.type.ProductCategoryType;
import vn.mos.core.exceptions.BusinessException;

import java.util.List;

@Service
public class CategoryShareService extends BaseService {
  @Autowired CategoryEntityRepository categoryEntityRepository;

  public CategoryEntity getCategoryById(Long id) {
    String key = redisKey.format(redisKey.CATEGORY_BY_ID, id);
    CategoryEntity category = cacheUtil.getJson(key, CategoryEntity.class);
    if (category == null) {
      category = categoryEntityRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, category);
    }
    return category;
  }

  public CategoryEntity getExistCategoryById(Long id) {
    CategoryEntity category = getCategoryById(id);
    if (category == null) {
      throw new BusinessException(BusinessErrorCode.CATEGORY_NOT_FOUND);
    }
    return category;
  }

  public List<CategoryEntity> getAllCategories(Long parentId, String name, ActiveStatus status, ProductCategoryType type) {
    return categoryEntityRepository.findAll(findCategories(parentId, name, status, type));
  }

  public Specification<CategoryEntity> findCategories(Long parentId, String name, ActiveStatus status, ProductCategoryType type) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction(); // Default to always true
      if (parentId != null) {
        predicate = cb.and(predicate, cb.equal(root.get("parentId"), parentId)); // Filter by user
      }
      if (StringUtils.isNotBlank(name)) {
        predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")); // Filter by name
      }
      if (status != null) {
        predicate = cb.and(predicate, cb.equal(root.get("status"), status)); // Filter by status
      }
      if (type != null) {
        predicate = cb.and(predicate, cb.equal(root.get("type"), type)); // Filter by type
      }
      return predicate;
    };
  }
}
