package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import entidades.Produto;
import enums.TamanhoCalcadosEnum;
import enums.TamanhoRoupasLetrasEnum;

public class ManterEstoqueMB {

	private List<String> listaTamanhos;
	private Produto produto;

	@PostConstruct
	public void init() {

	}

	public void listaTamanhosPorCategoria() {
		if (produto.getCategoria() != null) {
			listaTamanhos = new ArrayList<>();

			if (produto.getCategoria().getNoCategoria().equalsIgnoreCase("Calçados")) {

				for (TamanhoCalcadosEnum enumCalcado : TamanhoCalcadosEnum.values()) {
					listaTamanhos.add(enumCalcado.getLetra());
				}
			} else {
				for (TamanhoRoupasLetrasEnum enumRoupa : TamanhoRoupasLetrasEnum.values()) {
					listaTamanhos.add(enumRoupa.getLetra());
				}
			}

		}

	}

}
