package dev.abhiroopsantra.productservice.service;

import dev.abhiroopsantra.productservice.dto.ProductRequest;
import dev.abhiroopsantra.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getProducts();
}
