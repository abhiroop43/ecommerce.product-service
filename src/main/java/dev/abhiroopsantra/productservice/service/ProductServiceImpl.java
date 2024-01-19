package dev.abhiroopsantra.productservice.service;

import dev.abhiroopsantra.productservice.dto.ProductRequest;
import dev.abhiroopsantra.productservice.dto.ProductResponse;
import dev.abhiroopsantra.productservice.model.Product;
import dev.abhiroopsantra.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service @RequiredArgsConstructor @Slf4j public class ProductServiceImpl implements ProductService {
    private final ModelMapper       modelMapper;
    private final ProductRepository productRepository;

    @Override public void createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);

        productRepository.save(product);
        log.info("Product {} created successfully", product.getId());
    }

    @Override public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }
}
