package controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import entidades.Venda;
import servicos.VendaService;

@ManagedBean
@ViewScoped
public class ManterPrincipalMB {
	@EJB
	VendaService vendaService;

	private List<Venda> listaVendas;

	private LineChartModel dateModel;

	@PostConstruct
	public void init() {
		preencheListaVendas();
		createDateModel();
	}

	public LineChartModel getDateModel() {
		return dateModel;
	}

	public void preencheListaVendas() {
		listaVendas = vendaService.listarTodos();

	}

	private void createDateModel() {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		for (Venda venda : listaVendas) {

			series1.set(df.format(venda.getDataVenda()), venda.getValorTotal());
		}

		dateModel.addSeries(series1);

		dateModel.setTitle("Para detalhes aumento o zoom");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Vendas");

		DateAxis axis = new DateAxis("Dias");
		if (!listaVendas.isEmpty()) {
			Integer tamanhoLista = listaVendas.size();
			axis.setMin(df.format(listaVendas.get(0).getDataVenda()));
			axis.setMax(df.format(listaVendas.get(tamanhoLista - 1).getDataVenda()));
		}
		axis.setTickFormat("%b %#d, %Y");
		dateModel.getAxes().put(AxisType.X, axis);
	}

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

}
