package com.lamld.ossellercentermcrs.domain.shareService.user;

import com.lamld.ossellercentermcrs.domain.entities.user.AccountEntity;
import com.lamld.ossellercentermcrs.domain.repositories.user.AccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mos.core.exceptions.BusinessException;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.BusinessErrorCode;

import java.util.List;

@Service
public class AccountShareService extends BaseService {

  @Autowired protected AccountEntityRepository accountRepository;

  public AccountEntity getUserAccountByUsername(String username) {
    String key = redisKey.format(redisKey.ACCOUNT_BY_USERNAME, username);
    AccountEntity account = cacheUtil.getJson(key, AccountEntity.class);
    if (account == null) {
      account = accountRepository.findByUsername(username);
      cacheUtil.setJson(key, account);
    }
    return account;
  }

  public AccountEntity getExistUserAccountByUsername(String username) {
    String key = redisKey.format(redisKey.ACCOUNT_BY_USERNAME, username);
    AccountEntity account = cacheUtil.getJson(key, AccountEntity.class);
    if (account == null) {
      account = accountRepository.findByUsername(username);
      cacheUtil.setJson(key, account);
    }
    if (account == null) {
      throw new BusinessException(BusinessErrorCode.ACCOUNT_NOT_FOUND);
    }
    return account;
  }

  public AccountEntity getUserAccountById(Long id) {
    String key = redisKey.format(redisKey.ACCOUNT_BY_ID, id);
    AccountEntity account = cacheUtil.getJson(key, AccountEntity.class);
    if (account == null) {
      account = accountRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, account);
    }
    return account;
  }

  public AccountEntity getExistUserAccountById(Long id) {
    String key = redisKey.format(redisKey.ACCOUNT_BY_ID, id);
    AccountEntity account = cacheUtil.getJson(key, AccountEntity.class);
    if (account == null) {
      account = accountRepository.findById(id).orElse(null);
      cacheUtil.setJson(key, account);
    }
    if (account == null) {
      throw new BusinessException(BusinessErrorCode.ACCOUNT_NOT_FOUND);
    }
    return account;
  }

  public AccountEntity saveUserAccount(AccountEntity account) {
    removeAccountCache(account);
    return accountRepository.saveAndFlush(account);
  }

  public void removeAccountCache(AccountEntity account) {
    List<String> keys = List.of(
        redisKey.format(redisKey.ACCOUNT_BY_USERNAME, account.getUsername()),
        redisKey.format(redisKey.ACCOUNT_BY_ID, account.getId())
    );

    cacheUtil.delete(keys);
  }
}
