package br.com.ftec.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long id;
    @Column(name = "role")
    private String role;
}
