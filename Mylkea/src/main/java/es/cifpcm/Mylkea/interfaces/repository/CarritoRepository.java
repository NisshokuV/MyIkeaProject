package es.cifpcm.Mylkea.interfaces.repository;

import es.cifpcm.Mylkea.models.Carrito;
import es.cifpcm.Mylkea.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Integer>
{
    Carrito findByUsuario(Usuario usuario);
}
