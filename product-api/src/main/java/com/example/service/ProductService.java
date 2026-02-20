package com.example.service;

// Import du modèle Product
import com.example.model.Product;

// Import du repository qui permet d'accéder à la base de données
import com.example.repository.ProductRepository;

// Annotation qui indique que cette classe est un service Spring
import org.springframework.stereotype.Service;

import java.util.List;

// Indique à Spring que cette classe contient de la logique métier
@Service
public class ProductService {

    // Déclaration du repository pour interagir avec la base de données
    private final ProductRepository productRepository;

    // Constructeur avec injection de dépendance (Spring injecte automatiquement le repository)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Méthode pour récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Méthode pour récupérer un produit par son ID
    // Si le produit n'existe pas, retourne null
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Méthode pour créer un nouveau produit
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Méthode pour mettre à jour un produit existant
    public Product updateProduct(Long id, Product productDetails) {

        // On récupère le produit existant
        Product product = getProductById(id);

        // Si le produit existe, on met à jour ses informations
        if (product != null) {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());

            // On sauvegarde les modifications en base
            return productRepository.save(product);
        }

        // Si le produit n'existe pas, on retourne null
        return null;
    }

    // Méthode pour supprimer un produit par son ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}