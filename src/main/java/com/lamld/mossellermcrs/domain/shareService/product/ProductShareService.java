package com.lamld.mossellermcrs.domain.shareService.product;

import com.lamld.mossellermcrs.domain.repositories.product.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mos.core.base.BaseService;

@Service
public class ProductShareService extends BaseService {
  @Autowired ProductEntityRepository productEntityRepository;
}
