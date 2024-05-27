package br.com.techlead.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
