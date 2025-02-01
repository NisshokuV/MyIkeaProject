package es.cifpcm.Mylkea.interfaces.IServices;

import es.cifpcm.Mylkea.models.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsuarioService extends UserDetailsService
{
    public Usuario getUserById(Integer id);
    public Usuario getUserByUsername(String username);
    public void createUser(String username, String password);
    public void deleteUser(int id);
    public List<Usuario> getAllUsers();
}
