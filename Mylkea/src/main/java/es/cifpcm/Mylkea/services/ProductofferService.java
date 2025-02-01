package es.cifpcm.Mylkea.services;

import es.cifpcm.Mylkea.interfaces.IServices.IProductofferService;
import es.cifpcm.Mylkea.interfaces.repository.ProductofferRepository;
import es.cifpcm.Mylkea.models.Productoffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductofferService implements IProductofferService
{
    @Autowired
    ProductofferRepository repo;

    @Override
    public void createProduct(Productoffer product)
    {
        repo.save(product);
    }

    @Override
    public List<Productoffer> ListProducts() {
        return repo.findAll();
    }

    @Override
    public Productoffer getProductById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
