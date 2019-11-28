package br.com.ftec.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private String imageUrl;
    private String title;
    private long price;

    public Product() {}

    public Product(String description, String imageUrl, long price) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, description='%s', imageUrl='%s', price='%d']", id, description, imageUrl, price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
