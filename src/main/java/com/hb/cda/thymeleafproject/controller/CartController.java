package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Controller
public class CartController {
    private final CartService cartService;
    private final ProductRepository productRepo;

    public CartController(CartService cartService, ProductRepository productRepo) {
        this.cartService = cartService;
        this.productRepo = productRepo;
    }

    @GetMapping("/cart")
    public String showCart(Model model, @AuthenticationPrincipal User user) {
        Map<Product, Integer> cart = cartService.getCart();
        Double total = cartService.getTotal();

        model.addAttribute("cartItems", cart);
        model.addAttribute("total", total);

        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable"));
        cartService.addToCart(product);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{productId}")
    public String removeFromCart(@PathVariable String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable"));
        cartService.removeFromCart(product);

        return "redirect:/cart";
    }

    @PostMapping("/cart/validate")
    public String validateCart() {
        cartService.validateCart();
        return "redirect:/products";
    }

}
