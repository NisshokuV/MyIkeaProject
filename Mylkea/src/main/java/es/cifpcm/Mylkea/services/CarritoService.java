package es.cifpcm.Mylkea.services;

import es.cifpcm.Mylkea.interfaces.repository.CarritoRepository;
import es.cifpcm.Mylkea.models.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cifpcm.Mylkea.interfaces.IServices.ICarritoService;
import es.cifpcm.Mylkea.services.PedidoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService implements ICarritoService
{
    @Autowired
    CarritoRepository repo;
    @Autowired
    UsuarioService uS;
    @Autowired
    ProductofferService pS;
    @Autowired
    PedidoService peS;
    @Override
    public void addProductToCarrito(int idProduct, int idUser)
    {
        Usuario user = uS.getUserById(idUser);
        Carrito carrito = user.getCarrito();

        // Verificar si el carrito existe, si no, crear uno nuevo
        if (carrito == null)
        {
            carrito = new Carrito();
            carrito.setUsuario(user); // Asociar el carrito con el usuario
            user.setCarrito(carrito); // Establecer el carrito en el usuario
            repo.save(carrito); // Guardar el nuevo carrito en la base de datos
        }

        // Crear un nuevo producto en el carrito
        CarritoProduct carritoProduct = new CarritoProduct();
        carritoProduct.setCarrito(carrito);
        carritoProduct.setProducto(pS.getProductById(idProduct));

        // Añadir el producto al carrito
        List<CarritoProduct> carritoItems = carrito.getItems();

        carritoItems.add(carritoProduct);

        carrito.setItems(carritoItems);
        // Guardar el carrito con el nuevo producto
        repo.save(carrito);
    }

    @Override
    public void removeProductFromCarrito(int idProduct, int idUser)
    {
        Usuario user = uS.getUserById(idUser);
        Carrito carrito = user.getCarrito();
        List<CarritoProduct> carritoItems = carrito.getItems();

        for (CarritoProduct carritoProduct : carritoItems)
        {
            if (carritoProduct.getProducto().getId() == idProduct)
            {
                carritoItems.remove(carritoProduct);
                break;
            }
        }
        repo.save(carrito);
    }

    @Override
    public void payCarrito(int idUser)
    {
        Usuario user = uS.getUserById(idUser);
        List<CarritoProduct> carritoItems = this.getCarritoItems(idUser);
        List<PedidoProduct> pedidoItems = new ArrayList<>();

        for (CarritoProduct carritoProduct : carritoItems)
        {
            PedidoProduct pedidoProduct = new PedidoProduct();
            pedidoProduct.setProducto(carritoProduct.getProducto());
            pedidoItems.add(pedidoProduct);
        }

        peS.createPedido(user, pedidoItems, this.getTotalPrice(idUser));

        // empty carrito
        Carrito carrito = user.getCarrito();
        carrito.getItems().clear();
        repo.save(carrito);
    }

    @Override
    public List<CarritoProduct> getCarritoItems(int idUser)
    {
        Usuario user = uS.getUserById(idUser);
        Carrito carrito = user.getCarrito();
        List<CarritoProduct> carritoItems = carrito.getItems();
        return carritoItems;
    }

    @Override
    public Double getTotalPrice(int idUser)
    {
        List<CarritoProduct> carritoItems = this.getCarritoItems(idUser);
        Double totalPrice = 0.0;
        for (CarritoProduct carritoProduct : carritoItems)
        {
            totalPrice += carritoProduct.getProducto().getProductPrice();
        }
        return totalPrice;
    }

    @Override
    public Carrito getCarrito(Usuario user)
    {
        return repo.findByUsuario(user);
    }
}
