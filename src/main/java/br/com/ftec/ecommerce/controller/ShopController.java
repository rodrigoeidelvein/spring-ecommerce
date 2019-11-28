package br.com.ftec.ecommerce.controller;

import br.com.ftec.ecommerce.model.Cart;
import br.com.ftec.ecommerce.model.CartItem;
import br.com.ftec.ecommerce.model.Product;
import br.com.ftec.ecommerce.model.User;
import br.com.ftec.ecommerce.repository.CartItemRepository;
import br.com.ftec.ecommerce.repository.CartRepository;
import br.com.ftec.ecommerce.repository.ProductRepository;
import br.com.ftec.ecommerce.repository.UserRepository;
import br.com.ftec.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    @GetMapping("/")
    public String ShopIndex(Model model) {
        List<Product> products = new ArrayList();
        for (Product product : productRepository.findAll()) {
            products.add(product);
            log.info(product.toString());
        }
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/products")
    public String ProductList(Model model) {
        List<Product> products = new ArrayList();
        for (Product product : productRepository.findAll()) {
            products.add(product);
            log.info(product.toString());
        }
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/products/details")
    public String ProductDetails(@RequestParam("id") int productId, Model model) {
        Product product = productRepository.findById(productId);
        model.addAttribute("product", product);
        log.info("Product found: " + product.toString());
        return "product-detail";
    }

    @PostMapping("/cart")
    public String PostCart(@RequestParam("productId") int productId, Authentication authentication) {
        log.info("Authenticated user: " + authentication.getName());
        User user = userService.findUserByEmail(authentication.getName());
        Cart cartExists = cartRepository.findByUser(user);
        Product product = productRepository.findById(productId);
        if (cartExists != null) {
            CartItem itemExists = cartItemRepository.findByProductAndCart(product, cartExists);
            if (itemExists != null) {
                itemExists.setQuantity(itemExists.getQuantity() + 1);
            } else {
                CartItem item = new CartItem(cartExists, 1, product);
            }

        }

        return "redirect:/products";
    }
}
