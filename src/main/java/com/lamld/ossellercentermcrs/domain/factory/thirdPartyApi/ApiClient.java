package com.lamld.ossellercentermcrs.domain.factory.thirdPartyApi;

import com.lamld.ossellercentermcrs.domain.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "json-placeholder", url = "https://jsonplaceholder.typicode.com")
public interface ApiClient {

    // Lấy thông tin user theo ID
    @GetMapping("/users/{id}")
    ResponseEntity<String> getUserById(@PathVariable("id") String id);

    // Tạo user mới
    @PostMapping("/users")
    ResponseEntity<String> createUser(@RequestBody User user);

    // Xóa user theo ID
    @DeleteMapping("/users/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") String id);
}