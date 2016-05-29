package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Estoque {

	@Id
	@ManyToOne
	@JoinColumn(name = "produto")
	private Produto produto;
	@Column(name = "qtd_minima")
	private Integer quantidadeMinima;
	@Column(name = "qtd_atual")
	private Integer quantidadeAtual;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public Integer getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(Integer quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public Estoque(Produto produto, Integer quantidadeMinima, Integer quantidadeAtual) {
		super();
		this.produto = produto;
		this.quantidadeMinima = quantidadeMinima;
		this.quantidadeAtual = quantidadeAtual;
	}

	public Estoque() {
		// TODO Auto-generated constructor stub
	}

}
