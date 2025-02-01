package es.cifpcm.Mylkea.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_product")
public class PedidoProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Productoffer producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public PedidoProduct() {}
    public PedidoProduct(Productoffer producto, Pedido pedido) {
        this.producto = producto;
        this.pedido = pedido;
    }
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Productoffer getProducto()
    {
        return producto;
    }

    public void setProducto(Productoffer producto)
    {
        this.producto = producto;
    }

    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }
}
