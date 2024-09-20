package com.hammouda.observability.controller;
import com.hammouda.observability.entities.Product;
import com.hammouda.observability.model.Post;
import com.hammouda.observability.repository.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class ProductController {
    private final ProductRepository productRepository;
    private final RestClient restClient;

    public ProductController(ProductRepository productRepository,
                             RestClient.Builder restClient) {
        this.productRepository = productRepository;
        this.restClient = restClient
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    @GetMapping("/products")
    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }
    @PostMapping("/create")
    public void saveProducts(@RequestBody Product product)
    {
        productRepository.save(product);
    }
    @GetMapping("/posts")
    public List<Post> allPosts(){
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

}
