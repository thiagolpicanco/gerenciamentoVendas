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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((qtdAtual == null) ? 0 : qtdAtual.hashCode());
		result = prime * result + ((qtdMinima == null) ? 0 : qtdMinima.hashCode());
		result = prime * result + ((qtdPendente == null) ? 0 : qtdPendente.hashCode());
		result = prime * result + ((valorVenda == null) ? 0 : valorVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (qtdAtual == null) {
			if (other.qtdAtual != null)
				return false;
		} else if (!qtdAtual.equals(other.qtdAtual))
			return false;
		if (qtdMinima == null) {
			if (other.qtdMinima != null)
				return false;
		} else if (!qtdMinima.equals(other.qtdMinima))
			return false;
		if (qtdPendente == null) {
			if (other.qtdPendente != null)
				return false;
		} else if (!qtdPendente.equals(other.qtdPendente))
			return false;
		if (valorVenda == null) {
			if (other.valorVenda != null)
				return false;
		} else if (!valorVenda.equals(other.valorVenda))
			return false;
		return true;
	}
	
	

}
