package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import arquitetura.Entidade;

@Entity
public class ItemProduto extends Entidade {

	@Id
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "produto")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "venda")
	private Venda venda;
	@Column(name = "data")
	private Date data;
	@Column(name = "quantidade")
	private Integer quantidade;
	/** VENDA = 1 COMPRA = 2 **/
	@Column(name = "tipo_item")
	private Integer tipoItem;
	@ManyToOne
	@JoinColumn(name = "compra")
	private Compra compra;

	public ItemProduto() {
		// TODO Auto-generated constructor stub
	}

	public ItemProduto(Integer id, Produto produto, Venda venda, Date data, Integer quantidade, Integer tipoItem,
			Compra compra) {
		super();
		this.id = id;
		this.produto = produto;
		this.venda = venda;
		this.data = data;
		this.quantidade = quantidade;
		this.tipoItem = tipoItem;
		this.compra = compra;
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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
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

	public Integer getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(Integer tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
