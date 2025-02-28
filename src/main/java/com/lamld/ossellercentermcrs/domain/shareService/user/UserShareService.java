package com.lamld.ossellercentermcrs.domain.shareService.user;

import com.lamld.ossellercentermcrs.app.dto.auth.RegisterRequest;
import com.lamld.ossellercentermcrs.domain.entities.user.AccountEntity;
import com.lamld.ossellercentermcrs.domain.entities.user.UserEntity;
import com.lamld.ossellercentermcrs.domain.repositories.user.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mos.core.exceptions.BusinessException;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.BusinessErrorCode;

@Service
public class UserShareService extends BaseService {

  @Autowired private UserEntityRepository userRepository;
  @Autowired private WalletShareService walletShareService;

  public UserEntity getUserById(Long userId) {
    String key = redisKey.format(redisKey.USER_BY_ID, userId);
    UserEntity user = cacheUtil.getJson(key, UserEntity.class);
    if (user == null) {
      user = userRepository.findById(userId).orElse(null);
      cacheUtil.setJson(key, user);
    }
    return user;
  }

  public UserEntity getExistUserById(Long userId) {
    String key = redisKey.format(redisKey.USER_BY_ID, userId);
    UserEntity user = cacheUtil.getJson(key, UserEntity.class);
    if (user == null) {
      user = userRepository.findById(userId).orElse(null);
      cacheUtil.setJson(key, user);
    }
    if(user == null) {
      throw new BusinessException(BusinessErrorCode.USER_NOT_FOUND);
    }
    return user;
  }

  public UserEntity saveUser(UserEntity user) {
    removeUserCache(user);
    return userRepository.saveAndFlush(user);
  }

  public void removeUserCache(UserEntity user) {
    cacheUtil.delete(redisKey.format(redisKey.USER_BY_ID, user.getId()));
  }

  public UserEntity createUser(RegisterRequest request, AccountEntity accountEntity){
    UserEntity user = new UserEntity();
    user.setEmail(request.getEmail());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setDisplayName(request.getDisplayName());
    user.setAccountId(accountEntity.getId());
    user = saveUser(user);
    walletShareService.createUserWallet(user);
    return user;
  }
}
