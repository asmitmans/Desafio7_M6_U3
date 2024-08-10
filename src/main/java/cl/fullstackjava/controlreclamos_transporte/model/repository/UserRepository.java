package cl.fullstackjava.controlreclamos_transporte.model.repository;

import cl.fullstackjava.controlreclamos_transporte.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}