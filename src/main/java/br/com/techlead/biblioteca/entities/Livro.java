package br.com.techlead.biblioteca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(schema = "biblioteca", name = "livro")
@Data
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_livro")
	@SequenceGenerator(name = "gen_livro", sequenceName = "biblioteca.seq_livro", allocationSize = 1)
	private Long id;
	
	@Column(length = 255)
	private String descricao;
	
	@Column(length = 100)
	private String autor;
	
	@Embedded
	private AtributoPadrao atributoPadrao = new AtributoPadrao();

}
