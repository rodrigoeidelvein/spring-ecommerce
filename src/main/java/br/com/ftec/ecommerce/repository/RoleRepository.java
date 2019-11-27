package br.com.ftec.ecommerce.repository;

import br.com.ftec.ecommerce.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
