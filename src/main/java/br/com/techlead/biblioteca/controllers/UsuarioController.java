package br.com.techlead.biblioteca.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.techlead.biblioteca.entities.Usuario;
import br.com.techlead.biblioteca.exception.CustomAuthenticationException;
import br.com.techlead.biblioteca.repository.UsuarioRepository;
import br.com.techlead.biblioteca.services.EmailService;
import br.com.techlead.biblioteca.services.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

	private final UsuarioServiceImpl usuarioService;

	private final EmailService emailService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostMapping
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioService.create(usuario);
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.findByLogin(usuario.getLogin());

		if (user != null && passwordEncoder().matches(usuario.getPassword(), user.getPassword())) {
			return ResponseEntity.ok(user);
		} else {
			throw new CustomAuthenticationException("Login e/ou senha informada estão incorretos");
		}
	}
 
	@PostMapping(path = "/recuperaSenha")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void recuperarSenha(@RequestBody String email) throws Exception {
		Usuario user = usuarioRepository.findByLogin(email);

		if (user != null) {
			String newPassword = UUID.randomUUID().toString();
			emailService.enviar(user.getLogin(), "Recuperação de Senha", "Sua nova é: " + newPassword);
			
			user.setPassword(passwordEncoder().encode(newPassword));
			usuarioRepository.save(user);
		} else {
			throw new CustomAuthenticationException("Login não encontrado na base de dados");
		}
	}

	@PostMapping(path = "/alteraSenha")
	public void alteraSenha(@RequestBody String json) throws Exception {
		
        Gson gson = new Gson();
        Usuario userRequest = gson.fromJson(json, Usuario.class);

		Usuario user = usuarioRepository.findByLogin(userRequest.getLogin());
		if (user != null) {
		} else {
			throw new CustomAuthenticationException("Credenciais inválidas");
		}
	}
}
