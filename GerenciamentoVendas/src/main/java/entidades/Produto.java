package entidades;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;

public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -542059481551882370L;
	private Integer id;
	private String nome;
	private String fabricante;
	private Double valorCompra;
	private Double valorVenda;
	private String descricao;
	private Integer quantidade;
	private Integer categoria;
	private String tamanho;
	private String palavrasChaves;
	private File imagem;

	
	
	
	
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}




	public Produto(Integer id, String nome, String marca, Double valorCompra,
			Double valorVenda, String descricao, Integer quantidade,
			Integer categoria, String tamanho, String descricaoExtra) {
		super();
		this.id = id;
		this.nome = nome;
		this.fabricante = marca;
		this.valorCompra = valorCompra;
		this.valorVenda = valorVenda;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.tamanho = tamanho;
		this.palavrasChaves = descricaoExtra;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}

	



	public File getImagem() {
		return imagem;
	}




	public void setImagem(File imagem) {
		this.imagem = imagem;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getMarca() {
		return fabricante;
	}




	public void setMarca(String marca) {
		this.fabricante = marca;
	}




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




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public Integer getQuantidade() {
		return quantidade;
	}




	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}




	public Integer getCategoria() {
		return categoria;
	}




	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}




	public String getTamanho() {
		return tamanho;
	}




	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}




	public String getDescricaoExtra() {
		return palavrasChaves;
	}




	public void setDescricaoExtra(String descricaoExtra) {
		this.palavrasChaves = descricaoExtra;
	}
	
	
	
	
	
	
	
	
	
	
}
