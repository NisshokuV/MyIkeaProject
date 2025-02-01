package es.cifpcm.Mylkea.models;

import jakarta.persistence.*;
import es.cifpcm.Mylkea.models.Pedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username; // Nombre de usuario

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Lista de pedidos realizados por el usuario

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carrito carrito;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles = new ArrayList<>();

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Usuario() {}
    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
