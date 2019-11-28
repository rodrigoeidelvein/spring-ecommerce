package br.com.ftec.ecommerce.repository;

import br.com.ftec.ecommerce.model.Cart;
import br.com.ftec.ecommerce.model.CartItem;
import br.com.ftec.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    CartItem findByProductAndCart(Product product, Cart cart);
}
