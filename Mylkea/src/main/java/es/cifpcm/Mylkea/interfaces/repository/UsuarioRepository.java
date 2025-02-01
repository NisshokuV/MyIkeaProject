package es.cifpcm.Mylkea.interfaces.repository;

import es.cifpcm.Mylkea.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    Optional<Usuario> findById(Integer id);
    Usuario findByUsername(String username);
}
