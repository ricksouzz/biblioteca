package br.com.techlead.biblioteca.entities;

import java.io.Serializable;
import java.util.Date;

import br.com.techlead.biblioteca.entities.enums.DominioEvento;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Embeddable
@Data
public class AtributoPadrao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_usuario")
	private Long usuarioId;
	
	@Column(name = "id_usuario_inclusao")
	private Long usuarioIdInclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registro")
	private Date dataRegistro;

	@Column(name = "evento_registro")
	@Enumerated(EnumType.STRING)
	private DominioEvento evento;

}
