package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Estado;

public class EstadoDTO {
	
	private Long id;
	private String uf;

	public EstadoDTO() {
	}

	public EstadoDTO(Long id, String uf) {
		this.id = id;
		this.uf = uf;
	}

	public EstadoDTO(Estado estado) {
		id = estado.getId();
		uf = estado.getUf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
