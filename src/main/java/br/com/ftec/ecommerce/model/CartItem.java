package br.com.ftec.ecommerce.model;

import javax.persistence.*;
import br.com.ftec.ecommerce.model.Cart;

@Entity
@Table(name = "cartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public CartItem(){}

    public CartItem(Cart cart, Integer quantity, Product product) {
        this.cart = cart;
        this.quantity = quantity;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
