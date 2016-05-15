package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "PRODUTO")
@SequenceGenerator(name = "sq_produto", sequenceName = "sq_produto", allocationSize = 1, initialValue = 1)
public class Produto extends Entidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -542059481551882370L;
	@Id
	@GeneratedValue(generator = "sq_produto")
	@Column(name = "id")
	private Integer id;
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
	

	private Double valorVenda;

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
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}



}
