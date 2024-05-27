package br.com.techlead.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.techlead.biblioteca.entities.Usuario;
import br.com.techlead.biblioteca.exception.CustomAuthenticationException;
import br.com.techlead.biblioteca.interfaces.UsuarioService;
import br.com.techlead.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario create(Usuario usuario) {
		Usuario usuarioExiste = usuarioRepository.findByLogin(usuario.getLogin());
		
		if(usuarioExiste != null) {
			throw new CustomAuthenticationException("Usuário já existe!");
		}
		
		usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
		Usuario usuarioCriado = usuarioRepository.save(usuario);
		
		return usuarioCriado ;
	}
}
