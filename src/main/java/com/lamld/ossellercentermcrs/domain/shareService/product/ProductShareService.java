package com.lamld.ossellercentermcrs.domain.shareService.product;

import com.lamld.ossellercentermcrs.domain.entities.product.ProductEntity;
import com.lamld.ossellercentermcrs.domain.repositories.product.ProductEntityRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.ActiveStatus;
import vn.mos.core.base.type.BusinessErrorCode;
import vn.mos.core.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductShareService extends BaseService {
  @Autowired ProductEntityRepository productEntityRepository;

  public ProductEntity getProductById(Long id) {
    String key = redisKey.format(redisKey.PRODUCT_BY_ID, id);
    ProductEntity productEntity = cacheUtil.getJson(key, ProductEntity.class);
    if (productEntity == null) {
      productEntity = productEntityRepository.findById(id).orElse(null);
      if (productEntity!= null) {
        cacheUtil.setJson(key, productEntity);
      }
    }
    return productEntity;
  }

  public ProductEntity getExistProductById(Long id) {
    ProductEntity productEntity = getProductById(id);
    if (productEntity == null) {
      throw new BusinessException(BusinessErrorCode.PRODUCT_NOT_FOUND);
    }
    return productEntity;
  }

  public ProductEntity getExistProductByIdNoCache(Long id) {
    return productEntityRepository.findById(id).orElseThrow(() -> new BusinessException(BusinessErrorCode.PRODUCT_NOT_FOUND));
  }

  public ProductEntity save(ProductEntity productEntity) {
    removeCacheProduct(productEntity);
    return productEntityRepository.save(productEntity);
  }

  public void removeCacheProduct(ProductEntity productEntity) {
    List<String> keys = new ArrayList<>();
    keys.add(redisKey.format(redisKey.PRODUCT_BY_ID, productEntity.getId()));
    cacheUtil.delete(keys);
  }
  public Page<ProductEntity> getPageProduct(Long userId, Long groupId, String name, Long categoryId, ActiveStatus status, Pageable pageable){
    return productEntityRepository.findAll(filterProducts(userId,groupId, name, categoryId, status), pageable);
  }

  private Specification<ProductEntity> filterProducts(Long userId, Long groupId, String name, Long categoryId, ActiveStatus status) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction(); // Default to always true
      if (userId!= null) {
        predicate = cb.and(predicate, cb.equal(root.get("createdBy"), userId)); // Filter by user
      }
      if (groupId!= null) {
        predicate = cb.and(predicate, cb.equal(root.get("productGroupId"), groupId)); // Filter by group
      }
      if(StringUtils.isNotBlank(name)) {
        predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")); // Filter by name
      }
      if (categoryId!= null) {
        predicate = cb.and(predicate, cb.equal(root.get("categoryId"), categoryId)); // Filter by category
      }
      if (status!= null) {
        predicate = cb.and(predicate, cb.equal(root.get("status"), status)); // Filter by status
      }
      return predicate;
    };
  }
}
