package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Venda;

@Stateless
public class VendaDao extends GerencialDao<Venda> {

	public List<Venda> filtrarVendas(Venda filtro) {
		List<Venda> listaVendas = null;

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Venda p ");
		hql.append("where p.id is not null ");

		if (filtro.getNotaFiscal().getNuNotaFiscal() != null
				&& filtro.getNotaFiscal().getNuNotaFiscal().equals(new Integer(0))) {
			hql.append(" and p.notaFiscal.nuNotaFiscal =:nota ");
		}
		if (null != filtro.getFuncionarioResponsavel()) {
			hql.append(" and p.funcionarioResponsavel= :funcionario");
		}

		Query query = getEntityManager().createQuery(hql.toString());
		if (filtro.getNotaFiscal().getNuNotaFiscal() != null
				&& filtro.getNotaFiscal().getNuNotaFiscal().equals(new Integer(0))) {
			query.setParameter("nota", filtro.getNotaFiscal().getNuNotaFiscal());
		}

		if (null != filtro.getFuncionarioResponsavel()) {
			query.setParameter("funcionario", filtro.getFuncionarioResponsavel());
		}

		try {
			listaVendas = query.getResultList();
			return listaVendas;
		} catch (Exception e) {
			return null;
		}
	}

}
