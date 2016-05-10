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
@SequenceGenerator(name = "sq_login", sequenceName = "sq_login", allocationSize = 1, initialValue = 1)
public class Login extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4387348270323034384L;
	@Id
	@GeneratedValue(generator = "sq_login")
	@Column(name = "id")
	private Integer id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "senha")
	private String senha;
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;

	
	
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
