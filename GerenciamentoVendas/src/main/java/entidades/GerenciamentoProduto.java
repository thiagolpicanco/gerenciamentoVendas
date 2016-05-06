package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "gerenciamento_produto")
public class GerenciamentoProduto extends Entidade {

	private static final long serialVersionUID = 2531784648848646946L;

	@Id
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	@Column(name = "qtd_atual")
	private Integer qtdAtual;
	@Column(name = "qtd_minima")
	private Integer qtdMinima;
	@ManyToOne
	@JoinColumn(name = "id_estoque")
	private Estoque estoque;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQtdAtual() {
		return qtdAtual;
	}

	public void setQtdAtual(Integer qtdAtual) {
		this.qtdAtual = qtdAtual;
	}

	public Integer getQtdMinima() {
		return qtdMinima;
	}

	public void setQtdMinima(Integer qtdMinima) {
		this.qtdMinima = qtdMinima;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GerenciamentoProduto() {
	}

	@Override
	public String toString() {
		return "GerenciamentoProduto [id=" + id + ", produto=" + produto + ", qtdAtual=" + qtdAtual + ", qtdMinima="
				+ qtdMinima + "]";
	}

}
