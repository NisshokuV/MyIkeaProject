package es.cifpcm.Mylkea.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productoffer")
public class Productoffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Size(max = 512)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 512)
    private String productName;

    @Column(name = "product_price")
    private Float productPrice;

    @Size(max = 512)
    @Column(name = "product_picture", length = 512)
    private String productPicture;

    @NotNull
    @Column(name = "product_stock", nullable = false)
    private Integer productStock;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio idMunicipio;

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pedido_id")
//    private Pedido pedido;

    public Productoffer() {}

    public Productoffer(Integer id, String productName, Float productPrice, String productPicture, Integer productStock, Municipio idMunicipio)
    {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productPicture = productPicture;
        this.productStock = productStock;
        this.idMunicipio = idMunicipio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

//    public void setPedido(Pedido pedido) {
//        this.pedido = pedido;
//    }
}