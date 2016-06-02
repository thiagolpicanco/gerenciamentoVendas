package controladores;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@SessionScoped
public class ManterPrincipalMB {

	private LineChartModel dateModel;

	@PostConstruct
	public void init() {
		createDateModel();
	}

	public LineChartModel getDateModel() {
		return dateModel;
	}

	private void createDateModel() {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set("2014-01-01", 51);
		series1.set("2014-01-06", 22);
		series1.set("2014-01-12", 65);
		series1.set("2014-01-18", 74);
		series1.set("2014-01-24", 24);
		series1.set("2014-01-30", 51);

		dateModel.addSeries(series1);

		dateModel.setTitle("Para detalhes aumento o zoom");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Vendas");
		DateAxis axis = new DateAxis("Dias");
		axis.setMin("2013-12-31");
		axis.setMax("2014-01-31");
		axis.setTickFormat("%b %#d, %Y");
		dateModel.getAxes().put(AxisType.X, axis);
	}

}
