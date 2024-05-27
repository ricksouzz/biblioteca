package br.com.techlead.biblioteca.interfaces;

import java.util.List;

import br.com.techlead.biblioteca.entities.Livro;

public interface LivroService {

	List<Livro> listAll();

	Livro create(Livro livro);

	Livro update(Livro livro);

	void delete(Long id);

}
