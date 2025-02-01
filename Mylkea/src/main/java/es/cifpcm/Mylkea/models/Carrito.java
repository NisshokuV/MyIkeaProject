package es.cifpcm.Mylkea.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoProduct> items;

    public Carrito() {}
    public Carrito(Usuario usuario) {
        this.usuario = usuario;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CarritoProduct> getItems() {
        return items;
    }

    public void setItems(List<CarritoProduct> items) {
        this.items = items;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}