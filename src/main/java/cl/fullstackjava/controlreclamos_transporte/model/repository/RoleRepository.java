package cl.fullstackjava.controlreclamos_transporte.model.repository;

import cl.fullstackjava.controlreclamos_transporte.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}