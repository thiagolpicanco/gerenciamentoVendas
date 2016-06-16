package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "nota_fiscal")
@SequenceGenerator(name = "sq_nota_fiscal", sequenceName = "sq_nota_fiscal", allocationSize = 1, initialValue = 1)
public class NotaFiscal extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1233212282295200603L;

	@OneToOne(mappedBy = "notaFiscal")
	private Venda venda;

	@Id
	@GeneratedValue(generator = "sq_nota_fiscal")
	@Column(name = "nu_nota_fiscal")
	private Integer nuNotaFiscal;

	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionarioResponsavel;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public Integer getNuNotaFiscal() {
		return nuNotaFiscal;
	}

	public void setNuNotaFiscal(Integer nuNotaFiscal) {
		this.nuNotaFiscal = nuNotaFiscal;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
