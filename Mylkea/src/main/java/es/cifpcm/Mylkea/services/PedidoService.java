package es.cifpcm.Mylkea.services;

import es.cifpcm.Mylkea.interfaces.IServices.IPedidoService;
import es.cifpcm.Mylkea.interfaces.repository.PedidoRepository;
import es.cifpcm.Mylkea.models.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService implements IPedidoService
{
    @Autowired
    PedidoRepository repo;

    @Override
    public void createPedido(Usuario user, List<PedidoProduct> pedidos, Double totalPrice) {
        LocalDate fechaActual = LocalDate.now();
        Pedido pedido = new Pedido();
        pedido.setFecha(fechaActual);
        pedido.setPrecioTotal(totalPrice);
        pedido.setUsuario(user);

        // Asignar el pedido a cada producto
        for (PedidoProduct producto : pedidos) {
            producto.setPedido(pedido); // Asocia cada producto con el pedido
        }

        pedido.setPedidoProducts(pedidos);
        repo.save(pedido);
    }

    @Override
    public List<Pedido> getPedidosByUser(int idUser) {
        return List.of();
    }

    @Override
    public Pedido getPedidoById(int idPedido)
    {
        return repo.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
}
