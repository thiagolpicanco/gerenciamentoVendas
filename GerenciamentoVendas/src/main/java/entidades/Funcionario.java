package entidades;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3398693345378891333L;

	@Id
	@GeneratedValue()
	private Integer id;
	private String nome;
	private String cpfCnpj;
	private String telefone;
	private String celular;
	private String email;
	private String endereco;
	private Integer tipo;
	private Double salario;

	private String percentualComissao;

	@Transient
	private Double comissaoMes;

	public Funcionario() {
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(String percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

	public Double getComissaoMes() {
		return comissaoMes;
	}

	public void setComissaoMes(Double comissaoMes) {
		this.comissaoMes = comissaoMes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
