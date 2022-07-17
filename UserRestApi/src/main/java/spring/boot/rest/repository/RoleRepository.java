package spring.boot.rest.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.rest.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Page<Role> findAll(Pageable page);
}

