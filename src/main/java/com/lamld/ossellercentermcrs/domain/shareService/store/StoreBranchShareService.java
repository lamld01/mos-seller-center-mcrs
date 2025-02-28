package com.lamld.ossellercentermcrs.domain.shareService.store;

import com.lamld.ossellercentermcrs.app.dto.store.CreateStoreBranchRequest;
import com.lamld.ossellercentermcrs.app.dto.store.CreateStoreRequest;
import com.lamld.ossellercentermcrs.domain.entities.store.StoreBranchEntity;
import com.lamld.ossellercentermcrs.domain.entities.store.StoreEntity;
import com.lamld.ossellercentermcrs.domain.repositories.store.StoreBranchEntityRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.mos.core.exceptions.BusinessException;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.BusinessErrorCode;
import vn.mos.core.base.type.StoreBranchStatus;

import java.util.List;

@Service
public class StoreBranchShareService extends BaseService {

  @Autowired protected StoreBranchEntityRepository storeBranchRepository;

  public StoreBranchEntity getStoreBranchById(Long id) {
    String key = redisKey.format(redisKey.STORE_BRANCH_BY_ID, id);
    StoreBranchEntity storeBranch = cacheUtil.getJson(key, StoreBranchEntity.class);
    if (storeBranch == null) {
      storeBranch = storeBranchRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, storeBranch);
    }
    return storeBranch;
  }

  public StoreBranchEntity getExistStoreBranchById(Long id) {
    String key = redisKey.format(redisKey.STORE_BRANCH_BY_ID, id);
    StoreBranchEntity storeBranch = cacheUtil.getJson(key, StoreBranchEntity.class);
    if (storeBranch == null) {
      storeBranch = storeBranchRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, storeBranch);
    }
    if (storeBranch == null) {
      throw new BusinessException(BusinessErrorCode.STORE_BRANCH_NOT_FOUND);
    }
    return storeBranch;
  }

  public Page<StoreBranchEntity> getPageBranches(Long userId, Long storeId, String name, StoreBranchStatus status, String phoneNumber, Pageable pageable) {
    return storeBranchRepository.findAll(filterStoreBranches(userId,storeId, name, status, phoneNumber), pageable);
  }

  public Specification<StoreBranchEntity> filterStoreBranches(Long userId, Long storeId, String name, StoreBranchStatus status, String phoneNumber) {
    return (root, query, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();
      if (userId != null) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("createdBy"), userId));
      }
      if (storeId != null) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("storeId"), storeId));
      }
      if (StringUtils.isNotBlank(phoneNumber)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
      }
      if (StringUtils.isNotBlank(name)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }
      if (status != null) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
      }
      return predicate;

    };
  }


  public StoreBranchEntity createStoreBranch(StoreEntity store, CreateStoreRequest request) {
    return createStoreBranchEntity(store, request.getName(), request.getAddress(), request.getPhoneNumber());
  }

  public StoreBranchEntity createStoreBranch(StoreEntity store, CreateStoreBranchRequest request) {
    return createStoreBranchEntity(store, request.getName(), request.getAddress(), request.getPhoneNumber());
  }

  private StoreBranchEntity createStoreBranchEntity(StoreEntity store, String name, String address, String phoneNumber) {
    StoreBranchEntity storeBranchEntity = new StoreBranchEntity();
    storeBranchEntity.setStoreId(store.getId());
    storeBranchEntity.setName(name);
    storeBranchEntity.setAddress(address);
    storeBranchEntity.setPhoneNumber(phoneNumber);
    storeBranchEntity.setStatus(StoreBranchStatus.CLOSED);
    return saveStoreBranch(storeBranchEntity);
  }

  public StoreBranchEntity saveStoreBranch(StoreBranchEntity storeBranch) {
    removeStoreBranchCache(storeBranch);
    return storeBranchRepository.saveAndFlush(storeBranch);
  }

  public void removeStoreBranchCache(StoreBranchEntity storeBranch) {
    List<String> keys = List.of(
        redisKey.format(redisKey.format(redisKey.STORE_BRANCH_BY_ID, storeBranch.getId()), storeBranch.getId())
    );
    cacheUtil.delete(keys);
  }
}
