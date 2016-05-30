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
@Table(name = "SAIDA_PRODUTO")
@SequenceGenerator(name = "sq_saida_produto", sequenceName = "sq_saida_produto", allocationSize = 1, initialValue = 1)
public class SaidaProduto extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1272029906568489621L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "sq_saida_produto")
	private Integer id;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto", nullable = false),
			@JoinColumn(name = "tamanho", referencedColumnName = "tamanho", nullable = false) })
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "nu_venda")
	private Compra venda;
	@Column(name = "data_saida")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataSaida;
	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "observacao")
	private String observacao;

	public SaidaProduto() {
		// TODO Auto-generated constructor stub
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

	public Compra getVenda() {
		return venda;
	}

	public void setVenda(Compra venda) {
		this.venda = venda;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date data) {
		this.dataSaida = data;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
