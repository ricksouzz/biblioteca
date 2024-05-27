package br.com.techlead.biblioteca.entities.enums;

public enum DominioEvento {
	
	I("Inserido", 1), A("Alterado", 2), E("Excluido", 3);

	private String descricao;
	private int codigo;

	private DominioEvento(String descricao, int codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

	public String getDescricao() {
		return descricao;
	}

	public static DominioEvento getEventoByCodigo(int codigo) {
		DominioEvento[] eventos = DominioEvento.values();
		for (DominioEvento evento : eventos) {
			if (evento.getCodigo() == codigo) {
				return evento;
			}
		}
		return null;

	}
	
	public int getCodigo() {
		return codigo;
	}

}
