package entidades;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "estoque_produto")
@SequenceGenerator(name = "sq_estoque_produto", sequenceName = "sq_estoque_produto", allocationSize = 1, initialValue = 1)

public class EstoqueProduto extends Entidade {

	private static final long serialVersionUID = 2531784648848646946L;

	@Id
	@GeneratedValue(generator = "sq_estoque_produto")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_estoque")
	private Estoque estoque;
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Column(name = "qtd_atual")
	private Integer qtdAtual;
	@Column(name = "qtd_minima")
	private Integer qtdMinima;

	@Column(name = "tamanho")
	private String tamanho;

	@Column(name = "valor_compra")
	private Double valorCompra;
	@Column(name = "valor_venda")
	private Double valorVenda;

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

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

	public EstoqueProduto() {
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public String toString() {
		return "GerenciamentoProduto [id=" + id + ", produto=" + produto + ", qtdAtual=" + qtdAtual + ", qtdMinima="
				+ qtdMinima + "]";
	}

}
