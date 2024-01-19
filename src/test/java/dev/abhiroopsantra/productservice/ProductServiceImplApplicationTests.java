package dev.abhiroopsantra.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abhiroopsantra.productservice.dto.ProductRequest;
import dev.abhiroopsantra.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @Testcontainers @AutoConfigureMockMvc class ProductServiceImplApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;


    @DynamicPropertySource static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test void shouldCreateProduct() throws Exception {
        ProductRequest request              = getProductRequest();
        String         productRequestString = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
                                              .content(productRequestString)).andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    @Test void shouldGetProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")).andExpect(status().isOk());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("iPhone 12");
        productRequest.setDescription("Apple iPhone 12");
        productRequest.setPrice(BigDecimal.valueOf(750.50));

        return productRequest;
    }

}
