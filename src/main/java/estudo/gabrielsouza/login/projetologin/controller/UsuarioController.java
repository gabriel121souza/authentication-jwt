package estudo.gabrielsouza.login.projetologin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import estudo.gabrielsouza.login.projetologin.models.Usuario;
import estudo.gabrielsouza.login.projetologin.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	private final UsuarioRepository repository;
	private final PasswordEncoder encoder;
	
	public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Usuario>> listaTodos(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return ResponseEntity.ok().body(repository.save(usuario));
	}
	@GetMapping("/validarSenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password){
		
		Optional<Usuario> usuario  = repository.findByLogin(login);
		if(usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false); 
		}
		boolean valid = encoder.matches(password, usuario.get().getPassword());
		HttpStatus status =(valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}
}
