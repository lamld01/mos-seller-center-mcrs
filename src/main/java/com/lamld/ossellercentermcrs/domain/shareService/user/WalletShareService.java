package com.lamld.ossellercentermcrs.domain.shareService.user;

import com.lamld.ossellercentermcrs.domain.entities.user.UserEntity;
import com.lamld.ossellercentermcrs.domain.entities.user.WalletEntity;
import com.lamld.ossellercentermcrs.domain.repositories.user.WalletEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mos.core.exceptions.BusinessException;
import vn.mos.core.base.BaseService;
import vn.mos.core.base.type.BusinessErrorCode;
import vn.mos.core.base.type.CurrencyUnit;

import java.util.List;

@Service
public class WalletShareService extends BaseService {

  @Autowired protected WalletEntityRepository walletRepository;

  public WalletEntity getExistWalletByUserId(Long userId) {
    String key = redisKey.format(redisKey.WALLET_BY_USER_ID, userId);
    WalletEntity wallet = cacheUtil.getJson(key, WalletEntity.class);
    if (wallet == null) {
      wallet = walletRepository.findByUserId(userId);
      cacheUtil.setJson(key, wallet);
    }
    if (wallet == null) {
      throw new BusinessException(BusinessErrorCode.ACCOUNT_NOT_FOUND);
    }
    return wallet;
  }

  public WalletEntity saveWallet(WalletEntity wallet) {
    removeWalletCache(wallet);
    return walletRepository.saveAndFlush(wallet);
  }
  public void removeWalletCache(WalletEntity walletEntity) {
    List<String> keys = List.of(
        redisKey.format(redisKey.WALLET_BY_USER_ID, walletEntity.getUserId())
    );
    cacheUtil.delete(keys);
  }
  public WalletEntity createUserWallet(UserEntity user) {
    WalletEntity wallet = new WalletEntity();
    wallet.setUserId(user.getId());
    wallet.setBalance(0D);
    wallet.setCurrencyUnit(CurrencyUnit.VND);
    return saveWallet(wallet);
  }
}
