package br.com.techlead.biblioteca.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.techlead.biblioteca.entities.Livro;
import br.com.techlead.biblioteca.entities.Usuario;
import br.com.techlead.biblioteca.entities.enums.DominioEvento;
import br.com.techlead.biblioteca.exception.CustomAuthenticationException;
import br.com.techlead.biblioteca.interfaces.LivroService;
import br.com.techlead.biblioteca.repository.LivroRepository;
import br.com.techlead.biblioteca.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {

	private final HttpServletRequest httpRequest;

	private final String HEADERS_USER = "User";

	private final LivroRepository livroRepository;

	private final UsuarioRepository usuarioRepository;

	public Long getUserId() {
		return Long.parseLong(httpRequest.getHeader(HEADERS_USER).toString());
	}

	@Override
	public List<Livro> listAll() {
		return livroRepository.findAll();
	}

	@Override
	public Livro create(Livro livro) {
		if (livro.getId() != null) {
			throw new CustomAuthenticationException("Este livro já possui um ID, portanto já está cadastrado");
		}

		Long userId = getUserId();
		livro.getAtributoPadrao().setUsuarioId(userId);
		livro.getAtributoPadrao().setUsuarioIdInclusao(userId);
		livro.getAtributoPadrao().setDataRegistro(new Date());
		livro.getAtributoPadrao().setEvento(DominioEvento.I);

		return livroRepository.save(livro);
	}

	@Override
	public Livro update(Livro livro) {
		if (livro.getId() == null) {
			throw new CustomAuthenticationException("Este livro não está cadastrado para ser alterado!");
		}

		Long userId = getUserId();
		Usuario user = usuarioRepository.findById(userId).get();

		if (user.isAdministrator() || livro.getAtributoPadrao().getUsuarioIdInclusao().equals(userId)) {
	        return livroRepository.save(livro);
	    } else {
	        throw new CustomAuthenticationException("Você só pode excluir livros que cadastrou.");
	    }
	}

	@Override
	public void delete(Long id) {
		
		Long userId = getUserId();
		Usuario user = usuarioRepository.findById(userId).get();
		Livro livro = livroRepository.findById(userId).get();

		if (user.isAdministrator() || livro.getAtributoPadrao().getUsuarioIdInclusao().equals(userId)) {
			livroRepository.deleteById(id);
	    } else {
	        throw new CustomAuthenticationException("Você só pode excluir livros que cadastrou.");
	    }
	}
}
