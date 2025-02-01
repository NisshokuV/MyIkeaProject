package es.cifpcm.Mylkea.interfaces.repository;

import es.cifpcm.Mylkea.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}