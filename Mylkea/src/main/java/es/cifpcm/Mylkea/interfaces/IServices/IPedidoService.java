package es.cifpcm.Mylkea.interfaces.IServices;

import es.cifpcm.Mylkea.models.*;

import java.util.List;

public interface IPedidoService
{
    public void createPedido(Usuario user, List<PedidoProduct> products, Double totalPrice);
    public List<Pedido> getPedidosByUser(int idUser);
    public Pedido getPedidoById(int idPedido);
}
