package com.harjoitus.tyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.harjoitus.tyo.domain.CartItem;
import com.harjoitus.tyo.domain.CategoryRepository;
import com.harjoitus.tyo.domain.Product;
import com.harjoitus.tyo.domain.ProductRepository;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/welcomepage")
    public String welcomePage(Model model) {

        return "welcomepage";
    }

    @GetMapping("/productlist")
    public String showProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "productlist";
    }

    @GetMapping("/addproduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addproduct";
    }

    @PostMapping("/saveproduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());  // Re-add categories on error
            return "addproduct"; // Return to the form if validation fails
        }
        productRepository.save(product);
        return "redirect:/productlist";
    }

    @GetMapping("/deleteproduct/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/productlist";
    }

    @ModelAttribute("cart")
    public List<CartItem> initCart() {
        return new java.util.ArrayList<>();
    }

    @GetMapping("/shoppingcart")
    public String viewCart(@ModelAttribute("cart") List<CartItem> cart, Model model) {
        //price calculator
        double total = cart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cart);
        model.addAttribute("total", total);
        return "shoppingcart";
    }
// ADD TO CART

    @GetMapping("/shoppingcart/add/{id}")
    public String addToCartViaLink(@PathVariable("id") Long productId,
            @ModelAttribute("cart") List<CartItem> cart) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Already in cart? Increase quantity
            for (CartItem item : cart) {
                if (item.getProduct().getId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    return "redirect:/productlist";
                }
            }

            // Otherwise, add new item to the cart
            cart.add(new CartItem(product, 1));
        }

        return "redirect:/productlist";
    }

    @GetMapping("/shoppingcart/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long productId,
            @ModelAttribute("cart") List<CartItem> cart) {
        // Find the item in the cart
        cart.removeIf(item -> item.getProduct().getId().equals(productId));  // Removes the item by ID

        return "redirect:/shoppingcart";
    }
}
