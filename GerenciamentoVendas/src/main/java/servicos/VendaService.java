package servicos;

/**
 * 
 * @author thiago.picanco
 *
 */
import javax.ejb.Stateless;

import entidades.Produto;
import entidades.Venda;

@Stateless
public class VendaService {

	public void efetuarVenda(Venda venda) {
		venda.setValorTotal(this.getValorTotalVenda(venda));
		
		
	}

	public Double getValorTotalVenda(Venda venda) {
		Double total = 0d;
		for (Produto produto : venda.getListaProdutos()) {
			total += produto.getValorVenda();
		}

		return total;
	}

	public void geraComissao() {
		
		
	}
	
	
	
	
	
	

}
