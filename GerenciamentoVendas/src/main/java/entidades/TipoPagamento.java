package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "tipo_pagamento")
public class TipoPagamento extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6261897825792516039L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "no_tipo_pagamento")
	private String tipoPagamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
