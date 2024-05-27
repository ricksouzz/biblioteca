package br.com.techlead.biblioteca.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "biblioteca", name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_usuario")
	@SequenceGenerator(name = "gen_usuario", sequenceName = "biblioteca.seq_usuario", allocationSize = 1)
	private Long id;

	@Column(unique = true, length = 50)
	private String login;
	
	@Column(length = 100)
	private String nome;

	@Column(nullable = false)
	private String password;
	
    @Column(nullable = true, columnDefinition = "boolean default false")
	private boolean administrator;
	
	@ManyToMany
	private List<Role> roles;
}