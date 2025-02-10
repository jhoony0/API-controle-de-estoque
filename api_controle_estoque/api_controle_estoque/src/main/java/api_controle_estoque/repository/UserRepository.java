package api_controle_estoque.repository;

import api_controle_estoque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
