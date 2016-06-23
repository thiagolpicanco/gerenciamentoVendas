package controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.TipoPagamento;
import exceptions.PersistenciaException;
import persistence.TipoPagamentoDao;
import util.MensagensUtil;

@ManagedBean
@ViewScoped
public class ManterCondicoesPagamento {

	@EJB
	TipoPagamentoDao dao;

	TipoPagamento tipoPagamento;

	List<TipoPagamento> listaTipoPagamento;

	@PostConstruct
	public void init() {
		listaTipoPagamento = dao.listarTudo();
		tipoPagamento = new TipoPagamento();
	}

	public void gravar() {
		try {
			dao.gravar(tipoPagamento);
			this.init();
			MensagensUtil.adicionaMensagemSucesso("Condição registrada com sucesso");
		} catch (PersistenciaException e) {
			if (e.getCause().getCause().getCause().toString().contains("duplicate")) {
				MensagensUtil.adicionaMensagemErro("Erro ao cadastrar: Código ja cadastrado.");
			} else {
				MensagensUtil.adicionaMensagemErro("Erro ao cadastrar: " + e.getMessage());
			}

		}
	}

	public void limparCampos() {
		tipoPagamento = new TipoPagamento();
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<TipoPagamento> getListaTipoPagamento() {
		return listaTipoPagamento;
	}

	public void setListaTipoPagamento(List<TipoPagamento> listaTipoPagamento) {
		this.listaTipoPagamento = listaTipoPagamento;
	}

}
