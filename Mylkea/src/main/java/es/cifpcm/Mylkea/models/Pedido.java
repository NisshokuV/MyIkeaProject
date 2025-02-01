package es.cifpcm.Mylkea.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = true)
    private Double precioTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProduct> pedidoProducts;

    public Pedido() {}

    // Getters y Setters
    public List<PedidoProduct> getPedidoProducts() {
        return pedidoProducts;
    }
    public void setPedidoProducts(List<PedidoProduct> pedidoProducts) {
        this.pedidoProducts = pedidoProducts;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}