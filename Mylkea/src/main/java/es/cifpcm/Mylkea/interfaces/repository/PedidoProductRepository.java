package es.cifpcm.Mylkea.interfaces.repository;

import es.cifpcm.Mylkea.models.CarritoProduct;
import es.cifpcm.Mylkea.models.PedidoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProductRepository extends JpaRepository<PedidoProduct, Integer>
{

}
