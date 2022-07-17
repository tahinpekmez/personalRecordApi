package rest.api.swagger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.swagger.user.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    public Page<Role> findAll(Pageable page);
}
