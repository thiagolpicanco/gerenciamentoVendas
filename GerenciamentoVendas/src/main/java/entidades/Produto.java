package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import arquitetura.Entidade;

@Entity
@Table(name = "PRODUTO")
@SequenceGenerator(name = "sq_produto", sequenceName = "sq_produto", allocationSize = 1, initialValue = 1)
public class Produto extends Entidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -542059481551882370L;

	@EmbeddedId
	ProdutoPK id;

	@Column(name = "nome")
	private String nome;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	@Column(name = "descricao")
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "categoria")
	private CategoriaProduto categoria;
	@Column(name = "valor_venda")
	private Double valorVenda;

	@Column(name = "qtd_minima")
	private Integer qtdMinima;
	@Column(name = "qtd_atual")
	private Integer qtdAtual;

	@Transient
	private Integer qtdPendente;

	public Integer getQtdPendente() {
		return qtdPendente;
	}

	public void setQtdPendente(Integer qtdPendente) {
		this.qtdPendente = qtdPendente;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Produto() {
		this.id = new ProdutoPK();

	}

	public ProdutoPK getId() {
		return id;
	}

	public void setId(ProdutoPK id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	public Integer getQtdMinima() {
		return qtdMinima;
	}

	public void setQtdMinima(Integer qtdMinima) {
		this.qtdMinima = qtdMinima;
	}

	public Integer getQtdAtual() {
		return qtdAtual;
	}

	public void setQtdAtual(Integer qtdAtual) {
		this.qtdAtual = qtdAtual;
	}

}
