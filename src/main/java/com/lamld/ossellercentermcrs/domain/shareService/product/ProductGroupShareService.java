package com.lamld.ossellercentermcrs.domain.shareService.product;

import com.lamld.ossellercentermcrs.domain.entities.product.ProductGroupEntity;
import com.lamld.ossellercentermcrs.domain.repositories.product.ProductGroupEntityRepository;
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
public class ProductGroupShareService extends BaseService {
  @Autowired ProductGroupEntityRepository productGroupEntityRepository;

  public ProductGroupEntity getProductGroupById(Long id) {
    String key = redisKey.format(redisKey.PRODUCT_GROUP_BY_ID, id);
    ProductGroupEntity productGroupEntity = cacheUtil.getJson(key, ProductGroupEntity.class);
    if (productGroupEntity == null) {
      productGroupEntity = productGroupEntityRepository.findById(id).orElse(null);
      if (productGroupEntity!= null) {
        cacheUtil.setJson(key, productGroupEntity);
      }
    }
    return productGroupEntity;
  }

  public ProductGroupEntity getExistProductGroupById(Long id) {
    ProductGroupEntity productGroupEntity = getProductGroupById(id);
    if (productGroupEntity == null) {
      throw new BusinessException(BusinessErrorCode.PRODUCT_NOT_FOUND);
    }
    return productGroupEntity;
  }

  public ProductGroupEntity getExistProductGroupByIdNoCache(Long id) {
    return productGroupEntityRepository.findById(id).orElseThrow(() -> new BusinessException(BusinessErrorCode.PRODUCT_NOT_FOUND));
  }

  public ProductGroupEntity save(ProductGroupEntity productGroupEntity) {
    removeCacheProductGroup(productGroupEntity);
    return productGroupEntityRepository.save(productGroupEntity);
  }

  public void removeCacheProductGroup(ProductGroupEntity productGroupEntity) {
    List<String> keys = new ArrayList<>();
    keys.add(redisKey.format(redisKey.PRODUCT_GROUP_BY_ID, productGroupEntity.getId()));
    cacheUtil.delete(keys);
  }
  public Page<ProductGroupEntity> getPageProductGroup(Long userId, String name, Long categoryId, ActiveStatus status, Pageable pageable){
    return productGroupEntityRepository.findAll(filterProductGroup(userId, name, categoryId, status), pageable);
  }

  private Specification<ProductGroupEntity> filterProductGroup(Long userId, String name, Long categoryId, ActiveStatus status) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction(); // Default to always true
      if (userId!= null) {
        predicate = cb.and(predicate, cb.equal(root.get("createdBy"), userId)); // Filter by user
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
