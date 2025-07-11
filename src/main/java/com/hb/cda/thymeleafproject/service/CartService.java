package com.hb.cda.thymeleafproject.service;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    @Autowired
    private ProductRepository productRepo;

    // Le panier est stocké ici en tant que Map<Produit, Quantité>
    private Map<Product, Integer> cart = new HashMap<>();

    public Map<Product, Integer> getCart() {
        return cart;
    }
    public void addToCart(Product product) {
        cart.put(product, cart.getOrDefault(product, 0) + 1);
    }
    public void removeFromCart(Product product) {
        cart.remove(product);
    }
    public void validateCart() {
        // Met à jour les stocks des produits du panier
        List<Product> updatedProducts = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            product.setStock(product.getStock() - quantity);
            updatedProducts.add(product);

            System.out.println("- Stock de " + product.getName() + " mis à jour.");
        }
        productRepo.saveAll(updatedProducts);
        cart.clear(); // Vide le panier après la validation
    }

    public Double getTotal() {
        return cart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

}
