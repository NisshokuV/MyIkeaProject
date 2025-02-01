package es.cifpcm.Mylkea.services;

import es.cifpcm.Mylkea.interfaces.IServices.IUsuarioService;
import es.cifpcm.Mylkea.interfaces.repository.CarritoRepository;
import es.cifpcm.Mylkea.interfaces.repository.RoleRepository;
import es.cifpcm.Mylkea.interfaces.repository.UsuarioRepository;
import es.cifpcm.Mylkea.models.Carrito;
import es.cifpcm.Mylkea.models.Role;
import es.cifpcm.Mylkea.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService
{
    @Autowired
    UsuarioRepository repo;
    @Autowired
    CarritoRepository caRepo;
    @Autowired
    RoleRepository rolesRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public void deleteUser(int id)
    {
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();
        Usuario currentUser = repo.findByUsername(currentUsername);

        if (currentUser != null && currentUser.getId().equals(id)) {
            throw new IllegalArgumentException("No puedes eliminar tu propia cuenta.");
        }

        repo.deleteById(id);
    }

    @Override
    public void createUser(String username, String password) {
        Usuario user = new Usuario();
        user.setUsername(username);

        user.setPassword(passwordEncoder.encode(password));

        Role defaultRole = rolesRepository.findByName("USER");

        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setName("USER");
            rolesRepository.save(defaultRole);
        }
        Carrito carrito = new Carrito();
        carrito.setUsuario(user);
        user.setCarrito(carrito);
        user.getRoles().add(defaultRole);
        repo.save(user);
    }

    @Override
    public Usuario getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public Usuario getUserById(Integer id) {
        return repo.findById(id).orElse(null);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando autenticar: " + username);
        Usuario user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        String[] roles = user.getRoles().stream()
                .map(Role::getName)
                .toArray(String[]::new);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Override
    public List<Usuario> getAllUsers()
    {
        return repo.findAll();
    }
}