package estudo.gabrielsouza.login.projetologin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.gabrielsouza.login.projetologin.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Optional<Usuario> findByLogin(String login);

}
