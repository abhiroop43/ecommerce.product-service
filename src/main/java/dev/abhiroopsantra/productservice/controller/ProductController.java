package dev.abhiroopsantra.productservice.controller;

import dev.abhiroopsantra.productservice.dto.ApiResponse;
import dev.abhiroopsantra.productservice.dto.ProductRequest;
import dev.abhiroopsantra.productservice.dto.ProductResponse;
import dev.abhiroopsantra.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController @RequestMapping("/api/v1/products") @RequiredArgsConstructor public class ProductController {

    private final ProductService productService;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.errCode    = "201";
        apiResponse.errMessage = "Product created successfully";

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping @ResponseStatus(HttpStatus.OK) public ResponseEntity<ApiResponse> getProducts() {
        List<ProductResponse> products = productService.getProducts();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.errCode    = "200";
        apiResponse.errMessage = "Products fetched successfully";
        HashMap<String, Object> data = new HashMap<>();
        data.put("products", products);
        apiResponse.data = data;

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
