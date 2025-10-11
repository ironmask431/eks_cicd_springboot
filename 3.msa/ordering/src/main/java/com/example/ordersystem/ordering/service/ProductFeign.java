package com.example.ordersystem.ordering.service;

import com.example.ordersystem.ordering.dto.ProductDto;
import com.example.ordersystem.ordering.dto.ProductUpdateStockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//name은 eureka에 등록된 호출할 서비스의 이름
//url은 k8s에서 service명 -> 이게 우선적으로 처리됨. 로컬에서는 url="http://product-service" 이걸 찾지못해서 주문시 오류 발생함.

@FeignClient(name = "product-service") // 로컬용
//@FeignClient(name = "product-service", url="http://product-service") // 운영배포용
public interface ProductFeign {

    @GetMapping("/product/{productId}")
    ProductDto getProductById(@PathVariable Long productId, @RequestHeader("X-User-Id") String userId);

    @PutMapping("/product/updatestock")
    void updateProductStock(@RequestBody ProductUpdateStockDto productUpdateStockDto);
}
