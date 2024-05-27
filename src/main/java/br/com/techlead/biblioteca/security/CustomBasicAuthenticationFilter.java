package br.com.techlead.biblioteca.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.techlead.biblioteca.entities.Usuario;
import br.com.techlead.biblioteca.exception.CustomAuthenticationException;
import br.com.techlead.biblioteca.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String AUTHORIZATION = "Authorization";
	
	private static final String BASIC = "Basic";
	
	private final UsuarioRepository usuarioRepository;
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = getHeader(request);
		if(isBasicAuthentication(request)) {
            String base64Credentials = header.replace(BASIC, "").trim();
			String[] credentials = decodeBase64(base64Credentials).split(":");
			
			String username = credentials[0];
			String password = credentials[1];
			
			Usuario user = usuarioRepository.findByUsernameFetchRoles(username);
			if(user == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Usuário não existe");
				throw new CustomAuthenticationException("Usuário não existe");
			}
			
			System.out.println(user.getRoles());
			
			boolean valid = checkPassword(password, user.getPassword());
			if(!valid) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Senha incorreta");
				throw new CustomAuthenticationException("Senha incorreta");
			}
			
			setAuthentication(user);
		}
		
		filterChain.doFilter(request, response);
	}

	private void setAuthentication(Usuario user) {
		Authentication authentication = createAuthenticationToken(user);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private Authentication createAuthenticationToken(Usuario user) {
		 UsuarioPrincipal usuarioPrincipal = UsuarioPrincipal.create(user);
		 return new UsernamePasswordAuthenticationToken(usuarioPrincipal, null, usuarioPrincipal.getAuthorities());
	}

	private boolean checkPassword(String loginSenha, String usuarioSenha) {
		return passwordEncoder().matches(loginSenha, usuarioSenha);
	}

	private String decodeBase64(String base64) {
		byte[] decodeBytes = Base64.getDecoder().decode(base64);
		return new String(decodeBytes);
	}

	private boolean isBasicAuthentication(HttpServletRequest request) {
		String header = getHeader(request);
		return header != null && header.startsWith(BASIC);
	}

	private String getHeader(HttpServletRequest request) {
		return request.getHeader(AUTHORIZATION);
	}

}
