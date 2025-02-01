package es.cifpcm.Mylkea.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_product")
public class CarritoProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Productoffer producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pedido_id")
//    private Pedido pedido;

    public CarritoProduct() {}

    public CarritoProduct(Productoffer producto, Carrito carrito) {
        this.producto = producto;
        this.carrito = carrito;
    }

    public Integer getId() {
        return id;
    }

    public Productoffer getProducto() {
        return producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setProducto(Productoffer producto) {
        this.producto = producto;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}

