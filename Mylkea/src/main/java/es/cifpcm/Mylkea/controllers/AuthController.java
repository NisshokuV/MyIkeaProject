package es.cifpcm.Mylkea.controllers;

import es.cifpcm.Mylkea.models.Usuario;
import es.cifpcm.Mylkea.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    UsuarioService usersService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Nombre de usuario o contraseña incorrectos.");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new Usuario());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Usuario user, BindingResult bindingResult) {
        String result;
        if(bindingResult.hasErrors()){
            return "auth/register";
        } else {
            try{
                usersService.createUser(user.getUsername(), user.getPassword());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            result = "redirect:/auth/login";
        }
        return result;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String users(Model model, Principal principal) {
        String currentUsername = principal.getName();

        List<Usuario> usuarios = usersService.getAllUsers();

        usuarios.removeIf(u -> u.getUsername().equals(currentUsername));
        model.addAttribute("users", usuarios);
        return "auth/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return "redirect:/users";
    }
}