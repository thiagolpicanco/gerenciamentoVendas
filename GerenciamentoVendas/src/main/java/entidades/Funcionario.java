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
import javax.persistence.Transient;

import arquitetura.Entidade;

@Entity
@Table(name = "FUNCIONARIO")
@SequenceGenerator(name = "sq_funcionario", sequenceName = "sq_funcionario", allocationSize = 1, initialValue = 1)

public class Funcionario extends Entidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3398693345378891333L;

	@Id
	@GeneratedValue(generator = "sq_funcionario")
	@Column(name = "id")
	private Integer id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cnpj")
	private String cpf;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "celular")
	private String celular;
	@Column(name = "email")
	private String email;
	@Column(name = "endereco")
	private String endereco;
	@Column(name = "cargo")
	private String cargo;

	public Funcionario() {
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
		return cpf;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpf = cpfCnpj;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// public String getPercentualComissao() {
	// return percentualComissao;
	// }
	//
	// public void setPercentualComissao(String percentualComissao) {
	// this.percentualComissao = percentualComissao;
	// }
	//
	// public Double getComissaoMes() {
	// return comissaoMes;
	// }
	//
	// public void setComissaoMes(Double comissaoMes) {
	// this.comissaoMes = comissaoMes;
	// }
	//
	// public static long getSerialversionuid() {
	// return serialVersionUID;
	// }

}
