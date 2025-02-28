package com.lamld.ossellercentermcrs.domain.services.store;

import com.lamld.ossellercentermcrs.app.dto.store.CreateStoreBranchRequest;
import com.lamld.ossellercentermcrs.app.dto.store.UpdateStoreBranchRequest;
import com.lamld.ossellercentermcrs.app.response.store.StoreBranchResponse;
import com.lamld.ossellercentermcrs.domain.entities.store.StoreBranchEntity;
import com.lamld.ossellercentermcrs.domain.entities.store.StoreEntity;
import com.lamld.ossellercentermcrs.domain.shareService.store.StoreBranchShareService;
import com.lamld.ossellercentermcrs.domain.shareService.store.StoreShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.mos.core.base.type.StoreBranchStatus;

@Service
@RequiredArgsConstructor
public class StoreBranchService extends StoreBranchShareService {

  private final StoreShareService storeShareService;

  public StoreBranchResponse createStoreBranch(CreateStoreBranchRequest request) {
    StoreEntity store = storeShareService.getExistStoreById(request.getStoreId());
    StoreBranchEntity storeBranchEntity = createStoreBranch(store, request);
    storeBranchEntity = saveStoreBranch(storeBranchEntity);
    return new StoreBranchResponse(storeBranchEntity);
  }

  public StoreBranchResponse updateStoreBranch(Long id, UpdateStoreBranchRequest request) {
    StoreBranchEntity storeBranchEntity = getExistStoreBranchById(id);
    storeBranchEntity.setName(request.getName());
    storeBranchEntity.setStatus(request.getStatus());
    storeBranchEntity.setAddress(request.getAddress());
    storeBranchEntity.setPhoneNumber(request.getPhoneNumber());
    storeBranchEntity = saveStoreBranch(storeBranchEntity);
    return new StoreBranchResponse(storeBranchEntity);
  }

  public Page<StoreBranchResponse> getPageStoreBranches(Long storeId, String phoneNumber, String name, StoreBranchStatus status, Pageable pageable) {
    Long userId = getRequestUserId();
    Page<StoreBranchEntity> pageBranches = getPageBranches(userId,storeId, name, status, phoneNumber, pageable);
    return mapperUtil.mapPage(pageBranches, StoreBranchResponse.class);
  }

  public StoreBranchResponse getStoreBranchDetail(Long id) {
    StoreBranchEntity storeBranchEntity = getExistStoreBranchById(id);
    return new StoreBranchResponse(storeBranchEntity);
  }
}
