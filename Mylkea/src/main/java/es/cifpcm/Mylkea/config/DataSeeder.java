package es.cifpcm.Mylkea.config;

import es.cifpcm.Mylkea.interfaces.repository.RoleRepository;
import es.cifpcm.Mylkea.interfaces.repository.UsuarioRepository;
import es.cifpcm.Mylkea.models.Carrito;
import es.cifpcm.Mylkea.models.Role;
import es.cifpcm.Mylkea.models.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository rolesRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository rolesRepository,
                      UsuarioRepository usuarioRepository,
                      PasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Creando roles si no existen...");

        Role roleUser = rolesRepository.findByName("USER");
        if (roleUser == null) {
            roleUser = rolesRepository.saveAndFlush(new Role("USER")); // Usa saveAndFlush
        }

        Role roleManager = rolesRepository.findByName("MANAGER");
        if (roleManager == null) {
            roleManager = rolesRepository.saveAndFlush(new Role("MANAGER"));
        }

        Role roleAdmin = rolesRepository.findByName("ADMIN");
        if (roleAdmin == null) {
            roleAdmin = rolesRepository.saveAndFlush(new Role("ADMIN"));
        }

        // Ahora los roles están manejados por Hibernate antes de asignarlos a usuarios

        if (usuarioRepository.findByUsername("user@myikea.com") == null) {
            Usuario user = new Usuario();
            user.setUsername("user@myikea.com");
            user.setPassword(passwordEncoder.encode("Qwer123!"));
            user.getRoles().add(roleUser);
            Carrito carrito = new Carrito();
            carrito.setUsuario(user);
            user.setCarrito(carrito);
            usuarioRepository.save(user);
        }

        if (usuarioRepository.findByUsername("manager@myikea.com") == null) {
            Usuario manager = new Usuario();
            manager.setUsername("manager@myikea.com");
            manager.setPassword(passwordEncoder.encode("Qwer123!"));
            manager.getRoles().add(roleManager);
            Carrito carrito = new Carrito();
            carrito.setUsuario(manager);
            manager.setCarrito(carrito);
            usuarioRepository.save(manager);
        }

        if (usuarioRepository.findByUsername("admin1@myikea.com") == null) {
            Usuario admin1 = new Usuario();
            admin1.setUsername("admin1@myikea.com");
            admin1.setPassword(passwordEncoder.encode("Qwer123!"));
            admin1.getRoles().add(roleAdmin);
            Carrito carrito = new Carrito();
            carrito.setUsuario(admin1);
            admin1.setCarrito(carrito);
            usuarioRepository.save(admin1);
        }

        if (usuarioRepository.findByUsername("admin2@myikea.com") == null) {
            Usuario admin2 = new Usuario();
            admin2.setUsername("admin2@myikea.com");
            admin2.setPassword(passwordEncoder.encode("Qwer123!"));
            admin2.getRoles().add(roleAdmin);
            admin2.getRoles().add(roleManager);
            Carrito carrito = new Carrito();
            carrito.setUsuario(admin2);
            admin2.setCarrito(carrito);
            usuarioRepository.save(admin2);
        }
    }
}