package br.com.ftec.ecommerce.repository;

import br.com.ftec.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(long id);
}
