package entidades;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import arquitetura.Entidade;

@Entity
@Table(name = "ENTRADA_PRODUTO")
@SequenceGenerator(name = "sq_entrada_produto", sequenceName = "sq_entrada_produto", allocationSize = 1, initialValue = 1)
public class EntradaProduto extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5251064567188693421L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "sq_entrada_produto")
	private Integer id;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto", nullable = false),
			@JoinColumn(name = "tamanho", referencedColumnName = "tamanho", nullable = false) })
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "nu_compra")
	private Compra compra;
	@Column(name = "data_entrada")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date data;
	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "valor_unitario")
	private Double valorUnitario;

	@Column(name = "observacao")
	private String observacao;

	public EntradaProduto() {
		this.setId(null);
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
