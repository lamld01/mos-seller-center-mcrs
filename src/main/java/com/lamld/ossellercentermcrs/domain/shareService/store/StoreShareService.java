package com.lamld.ossellercentermcrs.domain.shareService.store;

import com.lamld.ossellercentermcrs.domain.entities.store.StoreEntity;
import com.lamld.ossellercentermcrs.domain.repositories.store.StoreEntityRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.BusinessErrorCode;
import vn.mos.core.exceptions.BusinessException;

import java.util.List;

@Service
public class StoreShareService extends BaseService {

  @Autowired protected StoreEntityRepository storeRepository;

  public StoreEntity getStoreById(Long id) {
    String key = redisKey.format(redisKey.STORE_BY_ID, id);
    StoreEntity store = cacheUtil.getJson(key, StoreEntity.class);
    if (store == null) {
      store = storeRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, store);
    }
    return store;
  }

  public StoreEntity getExistStoreById(Long id) {
    String key = redisKey.format(redisKey.STORE_BY_ID, id);
    StoreEntity store = cacheUtil.getJson(key, StoreEntity.class);
    if (store == null) {
      store = storeRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, store);
    }
    if(store == null) {
      throw new BusinessException(BusinessErrorCode.STORE_NOT_FOUND);
    }
    return store;
  }

  public Page<StoreEntity> getPage(Long userId, String phoneNumber, String name, Pageable pageable) {
    return storeRepository.findAll(filterStores(userId, phoneNumber, name), pageable);
  }

  public List<StoreEntity> findAll(Long userId, String phoneNumber, String name) {
    return storeRepository.findAll(filterStores(userId, phoneNumber, name));
  }

  public Specification<StoreEntity> filterStores(Long userId, String phoneNumber, String name) {
    return (root, query, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction(); // Default to always true

      if (userId != null) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("createdBy"), userId));
      }
      if (phoneNumber != null && !phoneNumber.isEmpty()) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber + "%"));
      }
      if (name != null && !name.isEmpty()) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }

      return predicate;
    };
  }

  public StoreEntity saveStore(StoreEntity store) {
    removeStoreCache(store);
    return storeRepository.saveAndFlush(store);
  }

  public void removeStoreCache(StoreEntity store) {
    List<String> keys = List.of(
        redisKey.format(redisKey.STORE_BY_ID, store.getId())
    );
    cacheUtil.delete(keys);
  }
}
