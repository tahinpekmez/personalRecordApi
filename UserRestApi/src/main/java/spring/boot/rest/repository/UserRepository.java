package spring.boot.rest.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.rest.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Page<User> findAll(Pageable page);

}
