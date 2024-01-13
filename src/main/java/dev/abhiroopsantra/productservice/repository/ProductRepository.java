package dev.abhiroopsantra.productservice.repository;

import dev.abhiroopsantra.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
