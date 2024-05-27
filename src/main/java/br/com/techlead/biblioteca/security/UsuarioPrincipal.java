package br.com.techlead.biblioteca.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.techlead.biblioteca.entities.Usuario;
import lombok.Getter;

@Getter
public class UsuarioPrincipal {

	private String login;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	private UsuarioPrincipal(Usuario usuario) {
		this.login = usuario.getLogin();
		this.password = usuario.getPassword();
		
		this.authorities = usuario.getRoles().stream().map(role -> {
			
			return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
		}).collect(Collectors.toList());
	}
	
	public static UsuarioPrincipal create(Usuario usuario) {
		return new UsuarioPrincipal(usuario);
	}
}
