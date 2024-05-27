package br.com.techlead.biblioteca.entities.enums;

public enum DominioTipoUsuario {
	
	ADMIN("ADMIN"), USER("USER");

	private String tipoUsuario;

	DominioTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public String getTipoUsuario() {
		return tipoUsuario;
	}

}
