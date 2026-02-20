package com.example.service;

// Import the Product model
import com.example.model.Product;

// Import the repository used to access the database
import com.example.repository.ProductRepository;

// Annotation that marks this class as a Spring service
import org.springframework.stereotype.Service;

import java.util.List;

// Indicates that this class contains business logic
@Service
public class ProductService {

    // Repository used to interact with the database
    private final ProductRepository productRepository;

    // Constructor with dependency injection (Spring automatically injects the repository)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Method to retrieve all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to retrieve a product by its ID
    // Returns null if the product does not exist
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Method to create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Method to update an existing product
    public Product updateProduct(Long id, Product productDetails) {

        // Retrieve the existing product
        Product product = getProductById(id);

        // If the product exists, update its fields
        if (product != null) {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());

            // Save the updated product to the database
            return productRepository.save(product);
        }

        // Return null if the product does not exist
        return null;
    }

    // Method to delete a product by its ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}