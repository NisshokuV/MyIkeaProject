package es.cifpcm.Mylkea.interfaces.IServices;

import es.cifpcm.Mylkea.models.Carrito;
import es.cifpcm.Mylkea.models.CarritoProduct;
import es.cifpcm.Mylkea.models.Usuario;

import java.util.List;

public interface ICarritoService
{
    public void addProductToCarrito(int idProduct, int idUser);
    public void removeProductFromCarrito(int idProduct, int idUser);
    public void payCarrito(int idUser);
    public List<CarritoProduct> getCarritoItems(int idUser);
    public Double getTotalPrice(int idUser);
    public Carrito getCarrito(Usuario user);
}
