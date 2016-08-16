
package br.gov.caixa.sitch.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import enums.TipoRelEnum;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;


public abstract class GerarRelatorioBean implements Serializable {

	private static final long serialVersionUID = 4288913089814146972L;

	public static <T> void gerarRelatorio(List<T> lista, String reportUrl, Map<String, Object> params, TipoRelEnum tipo) {
		String nomeArquivo = reportUrl.substring(reportUrl.lastIndexOf('/') + 1, reportUrl.lastIndexOf('.'));
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		String reportUrlReal = context.getExternalContext().getRealPath(reportUrl);
		Locale locale = new Locale("pt","br");
		params.put(JRParameter.REPORT_LOCALE, locale);
		params.put("PATH", reportUrlReal.substring(0, reportUrlReal.lastIndexOf('\\')+1));
		try {
			byte[] bytes = null;
			final JasperPrint jasperPrint = JasperFillManager.fillReport(reportUrlReal, params,
					(lista == null || lista.isEmpty()) ? new JREmptyDataSource() : new JRBeanCollectionDataSource(lista));
			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			if (tipo.equals(TipoRelEnum.PDF)) {
				JasperExportManager.exportReportToPdfStream(jasperPrint, output);
			} else if (tipo.equals(TipoRelEnum.EXCEL)) {
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				SimpleXlsReportConfiguration configurationXLS = new SimpleXlsReportConfiguration();
				configurationXLS.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
				configurationXLS.setWhitePageBackground(Boolean.FALSE);
				configurationXLS.setOnePagePerSheet(Boolean.FALSE);
				configurationXLS.setDetectCellType(Boolean.TRUE);
				exporter.setConfiguration(configurationXLS);
				exporter.exportReport();
			}

			bytes = output.toByteArray();
			response.setContentType(tipo.getContentType());
			response.setHeader("Content-disposition", "inline; filename=\"" + nomeArquivo + tipo.getExtensao() + "\"");
			response.setContentLength(bytes.length);

			final ServletOutputStream ouputStream = response.getOutputStream();

			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			context.responseComplete();
			context.renderResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
