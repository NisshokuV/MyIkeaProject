package es.cifpcm.Mylkea.controllers;

import es.cifpcm.Mylkea.models.Usuario;
import es.cifpcm.Mylkea.services.PedidoService;
import es.cifpcm.Mylkea.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class PedidoController
{
    @Autowired
    UsuarioService us;
    @Autowired
    PedidoService peS;

    @GetMapping("/pedidos")
    public String Pedidos(Model model, Principal principal)
    {
        String username = principal.getName();
        Usuario user = us.getUserByUsername(username);
        model.addAttribute("pedidos", user.getPedidos());
        return "pedidos/index";
    }
    @GetMapping("/pedidos/{Id}")
    public String Pedido(@PathVariable Integer Id, Model model)
    {
        model.addAttribute("pedido", peS.getPedidoById(Id));
        return "pedidos/details";
    }
}
