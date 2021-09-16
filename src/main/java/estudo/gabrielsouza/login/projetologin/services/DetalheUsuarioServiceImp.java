package estudo.gabrielsouza.login.projetologin.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import estudo.gabrielsouza.login.projetologin.data.DetalheUsuarioData;
import estudo.gabrielsouza.login.projetologin.models.Usuario;
import estudo.gabrielsouza.login.projetologin.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImp implements UserDetailsService {

	private final UsuarioRepository repository;
	public DetalheUsuarioServiceImp(UsuarioRepository repository) {
		this.repository = repository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByLogin(username);
		
		if(usuario.isEmpty())
			throw new UsernameNotFoundException("Usuario [" + username + "] nao encontrado" );
		
		return new DetalheUsuarioData(usuario);
	}

}
