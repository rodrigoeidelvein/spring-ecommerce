package br.com.ftec.ecommerce.repository;

import br.com.ftec.ecommerce.model.Cart;
import br.com.ftec.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUser(User user);
}
