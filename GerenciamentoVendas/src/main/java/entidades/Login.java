package entidades;

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
@Table(name = "LOGIN")
public class Login extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4387348270323034384L;
	@Id
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "senha")
	private String senha;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	
	
	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
