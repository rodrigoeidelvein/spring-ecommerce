package br.com.ftec.ecommerce.controller;

import br.com.ftec.ecommerce.model.Product;
import br.com.ftec.ecommerce.repository.ProductRepository;
import br.com.ftec.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/admin/products")
    public String AdminProduct(Model model) {
        List<Product> products = new ArrayList();
        for (Product product : productRepository.findAll()) {
            products.add(product);
            log.info(product.toString());
        }
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("/admin/add-product")
    public String AddProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/edit-product";
    }

    @PostMapping("/admin/add-product")
    public String AddProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/edit-product")
    public String EditProduct(@RequestParam("id") int productId, @RequestParam boolean editMode, Model model) {
        Product product = productRepository.findById(productId);
        if (product != null) {
            log.info("Product found: " + product.toString());
            model.addAttribute("editMode", editMode);
            model.addAttribute("product", product);
        }

        return "admin/edit-product";
    }

    @PostMapping("/admin/edit-product")
    public String EditProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/delete-product")
    public String DeleteProduct(@RequestParam("productId") int productId) {
        Product product = productRepository.findById(productId);
        if (product != null) {
            productRepository.delete(product);
        }

        return "redirect:/admin/products";
    }
}
