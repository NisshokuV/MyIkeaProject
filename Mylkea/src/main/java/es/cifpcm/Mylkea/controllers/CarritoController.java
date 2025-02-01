package es.cifpcm.Mylkea.controllers;

import es.cifpcm.Mylkea.models.Usuario;
import es.cifpcm.Mylkea.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class CarritoController
{
    @Autowired
    ProductofferService ps;
    @Autowired
    MunicipioService ms;
    @Autowired
    ProvinciaService pvs;
    @Autowired
    CarritoService cs;
    @Autowired
    UsuarioService us;

    @GetMapping("/carrito")
    public String Carrito(Model model, Principal principal)
    {
        String username = principal.getName();
        Usuario user = us.getUserByUsername(username);

        model.addAttribute("carritoProducts", cs.getCarritoItems(user.getId()));
        model.addAttribute("total", cs.getTotalPrice(user.getId()));
        return "carrito/index";
    }
    @GetMapping("/carrito/add/{idProduct}")
    public String AddProductToCarrito(@PathVariable Integer idProduct,Principal principal)
    {
        String username = principal.getName();
        Usuario user = us.getUserByUsername(username);
        Integer idUser = user.getId();

        cs.addProductToCarrito(idProduct, idUser);
        return "redirect:/carrito";
    }
    @GetMapping("/carrito/remove/{idProduct}")
    public String RemoveProductFromCarrito(@PathVariable Integer idProduct,Principal principal)
    {
        String username = principal.getName();
        Usuario user = us.getUserByUsername(username);
        Integer idUser = user.getId();

        cs.removeProductFromCarrito(idProduct, idUser);
        return "redirect:/carrito";
    }
    @GetMapping("/carrito/checkout")
    public String Checkout(Principal principal)
    {
        String username = principal.getName();
        Usuario user = us.getUserByUsername(username);
        Integer idUser = user.getId();

        cs.payCarrito(idUser);
        return "redirect:/pedidos";
    }
}
