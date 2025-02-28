package com.lamld.ossellercentermcrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan({"com.lamld", "vn.mos.core"})
public class MosSellerMcrsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MosSellerMcrsApplication.class, args);
  }

}
