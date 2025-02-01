package es.cifpcm.Mylkea.interfaces.IServices;

import es.cifpcm.Mylkea.models.Productoffer;

import java.util.List;

public interface IProductofferService
{
    public void createProduct(Productoffer product);
    public List<Productoffer> ListProducts();
    public Productoffer getProductById(int id);
}
