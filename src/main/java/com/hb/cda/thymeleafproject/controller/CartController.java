package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    private final CartService cartService;
    private final ProductRepository productRepo;

    public CartController(CartService cartService, ProductRepository productRepo) {
        this.cartService = cartService;
        this.productRepo = productRepo;
    }

    @GetMapping("/cart")
    public String showCart(Model model, @AuthenticationPrincipal User user, Product product) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("productTotal", cartService.getProductTotal(product));
        model.addAttribute("total", cartService.getTotal());

        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(Product product) {
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{productId}")
    public String removeFromCart(Product product) {
        return "redirect:/cart";
    }

    @PostMapping("/cart/validate")
    public String validateCart() {
        return "redirect:/home";
    }

}
