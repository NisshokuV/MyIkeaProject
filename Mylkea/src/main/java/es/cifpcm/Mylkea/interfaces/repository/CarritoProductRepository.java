package es.cifpcm.Mylkea.interfaces.repository;

import es.cifpcm.Mylkea.models.CarritoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductRepository extends JpaRepository<CarritoProduct, Integer>
{

}
