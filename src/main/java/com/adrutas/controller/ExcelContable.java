package com.adrutas.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.Salida;
import com.adrutas.model.SalidaDetalle;

import adrutas.com.Constante;

@WebServlet(name = "ExcelContable", urlPatterns = {"/excelContable"})
public class ExcelContable extends HttpServlet {
	private static final long serialVersionUID = 2607208416807650199L;
	private static final Logger log = Logger.getLogger(ExcelContable.class.getName());
	private static final String SPACES = "                              ";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cell cell;
		EntityManager em = null;
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        style.setDataFormat(wb.createDataFormat().getFormat("dd/mm/yy h:mm:ss;@"));
        HSSFSheet sContable = wb.createSheet("Contabilidad");
        HSSFSheet sPersonas = wb.createSheet("Personas");
        Row row = sContable.createRow(0);
        ServletOutputStream out = resp.getOutputStream();
        Object object;
		String salida = req.getParameter("salida");
        int fil1 = 0;
        int fil2 = 0;
        int col = 0;
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment;filename=contable_" + salida + ".xls");
		try {
			em = EntityManagerFactories.getEM();
			Salida salidaBean = em.createNamedQuery("Salida.findBySalida", Salida.class)
					.setParameter("salida", salida).getSingleResult();
	        String descripcion = (salidaBean.getDescripcion() + SPACES).substring(0, 30);
	        row.createCell(col++).setCellValue("fecha/hora");
	        row.createCell(col++).setCellValue("titulo");
	        row = sContable.createRow(++fil1);
	        col = 0;
	        cell = row.createCell(col++);
	        cell.setCellValue(salidaBean.getFechaInicio());
	        cell.setCellStyle(style);
	        row.createCell(col++).setCellValue(descripcion);
	        fil1++;
	        col = 0;
	        row = sContable.createRow(++fil1);
	        row.createCell(col++).setCellValue("bus");
	        row.createCell(col++).setCellValue("asiento");
	        row.createCell(col++).setCellValue("cuenta");
	        row.createCell(col++).setCellValue("id_recibo");
	        row.createCell(col++).setCellValue("fecha/hora");
	        row.createCell(col++).setCellValue("importe");
	        row.createCell(col++).setCellValue("seguro_dia");
	        row.createCell(col++).setCellValue("bono");
	        row.createCell(col++).setCellValue("estado");
	        row.createCell(col++).setCellValue("ingreso");
	        row.createCell(col++).setCellValue("pago");
	        row.createCell(col++).setCellValue("observacion");
	        row.createCell(col++).setCellValue("salida");
	        row.createCell(col++).setCellValue("concepto");
	        row = sPersonas.createRow(0);
	        col = 0;
	        row.createCell(col++).setCellValue("id_persona");
	        row.createCell(col++).setCellValue("dni");
	        row.createCell(col++).setCellValue("nombre");
	        row.createCell(col++).setCellValue("apellido1");
	        row.createCell(col++).setCellValue("apellido2");
	        for (SalidaDetalle bean: salidaBean.getSalidaDetalles()) {
	            row = sContable.createRow(++fil1);
	            col = 0;
	            if ((object = bean.getBus())!=null) {
	                row.createCell(col).setCellValue((Short) object);
	            }
	            col++;
	            if ((object = bean.getAsiento())!=null) {
	                row.createCell(col).setCellValue((Short) object);
	            }
	            col++;
	            row.createCell(col++).setCellValue("34" + Constante.nF2.format((Integer) bean.getId().getIdPersona()));
	            row.createCell(col++).setCellValue((Integer) bean.getRecibo().getIdRecibo());
	            cell = row.createCell(col++);
	            cell.setCellValue(bean.getRecibo().getFecha());
	            cell.setCellStyle(style);
	            row.createCell(col++).setCellValue(((BigDecimal) bean.getImporte()).doubleValue());
	            row.createCell(col++).setCellValue((object = bean.getSeguroDia())==null? false: (Byte) object==1);
	            if ((object = bean.getBono())!=null) {
	                row.createCell(col).setCellValue((String) object);
	            }
	            col++;
	            row.createCell(col++).setCellValue((String) bean.getFp());
	            row.createCell(col++).setCellValue(((BigDecimal) bean.getRecibo().getImporte()).doubleValue());
	            row.createCell(col++).setCellValue(((BigDecimal) bean.getPago()).doubleValue());
	            row.createCell(col++).setCellValue((String) bean.getObservacion());
	            row.createCell(col++).setCellValue(salida);
	            row.createCell(col++).setCellValue(descripcion);
	            row = sPersonas.createRow(++fil2);
	            col = 0;
	            row.createCell(col++).setCellValue(Constante.nF2.format((Integer) bean.getId().getIdPersona()));
	            row.createCell(col++).setCellValue((String) bean.getPersona().getDni());
	            row.createCell(col++).setCellValue((String) bean.getPersona().getNombre());
	            row.createCell(col++).setCellValue((String) bean.getPersona().getApellido1());
	            row.createCell(col++).setCellValue((String) bean.getPersona().getApellido2());
	        }
            wb.write(out);
		} catch (Exception e) {
			log.log(Level.SEVERE, "ListaAsientos", e);
		} finally {
			if (em!=null) {
				em.close();
			}
			wb.close();
			out.close();
		}
	}
}
