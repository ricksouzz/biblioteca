package br.com.techlead.biblioteca.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.techlead.biblioteca.entities.Livro;
import br.com.techlead.biblioteca.services.LivroServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/livros")
@RequiredArgsConstructor
public class LivroController {
	
	private final LivroServiceImpl livroServiceImpl;

	@PreAuthorize("hasRole('SELECT')")
	@GetMapping
	public List<Livro> listAll() {
		return  livroServiceImpl.listAll();
	}
	
	@PreAuthorize("hasRole('CREATE')")
	@PostMapping
	public Livro create(@RequestBody Livro livro) {
		return livroServiceImpl.create(livro);
	};
	
	@PreAuthorize("hasRole('UPDATE')")
	@PutMapping
	public Livro update(@RequestBody Livro livro) {
		return livroServiceImpl.update(livro);
	};
	
	@PreAuthorize("hasRole('DELETE')")
	@DeleteMapping
	public void delete(@RequestParam("id") Long id) {
		livroServiceImpl.delete(id);
	};
}
