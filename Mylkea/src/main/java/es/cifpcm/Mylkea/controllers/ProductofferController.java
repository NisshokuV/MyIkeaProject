package es.cifpcm.Mylkea.controllers;

import es.cifpcm.Mylkea.models.Productoffer;
import es.cifpcm.Mylkea.services.MunicipioService;
import es.cifpcm.Mylkea.services.ProductofferService;
import es.cifpcm.Mylkea.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductofferController
{
    @Autowired
    ProductofferService ps;
    @Autowired
    MunicipioService ms;
    @Autowired
    ProvinciaService pvs;

    @GetMapping("/")
    public String index(Model model)
    {
        return "index";
    }
    @GetMapping("/products")
    public String Products(Model model)
    {
        model.addAttribute("products", ps.ListProducts());
        return "products/index";
    }
    @GetMapping("/products/{Id}")
    public String Product(@PathVariable int Id, Model model)
    {
        model.addAttribute("product", ps.getProductById(Id));
        return "products/details";
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/products/create")
    public String Product(Model model)
    {
        model.addAttribute("municipios", ms.getMunicipios());
        model.addAttribute("provincias", pvs.getProvincias());
        return "products/create";
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/products/create")
    public String Product(Model model, Productoffer product)
    {
        ps.createProduct(product);
        return "products/create";
    }
}
