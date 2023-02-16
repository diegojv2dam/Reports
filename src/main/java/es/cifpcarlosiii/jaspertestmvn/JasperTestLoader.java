/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcarlosiii.jaspertestmvn;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author juanvi
 */
public class JasperTestLoader {
//    private static final String CON_STR = "jdbc:mysql://localhost:3306/pruebareportes";
//    private static final String USER = "root";
//    private static final String PASS = "";    92.168.1.97:5432/bitnami_odoo"
    private static final String CON_STR = "jdbc:postgresql://192.168.37.6/DBGrupon5";
    private static final String USER = "odoo";
    private static final String PASS = "odoo";
//    private static final String REPORT = "report3.jasper"; //"resources/reports/report3.jasper";    
//    private static final String REPORT_JXML = "report2.jrxml"; //"resources/reports/report3.jrxml";   
//    private static final String SUBREPORT_JXML = "report2_subreport1.jrxml";
    private static final String REPORT_JXML = "Report_diego_1.jrxml"; //"resources/reports/report3.jrxml";   
//    private static final String SUBREPORT_JXML = "report2_subreport1.jrxml";  
    private String reportJxml;

    private JasperReport reporte;
    private JasperPrint jasperPrint;

    JasperTestLoader(String reportJxml) {
        this.reportJxml = reportJxml;
    }
    
    public void load() throws ClassNotFoundException, SQLException, JRException {
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        Connection conexion = (Connection) DriverManager.getConnection(CON_STR, USER, PASS);

        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(reportJxml)); //new File(REPORT_JXML)
        reporte = JasperCompileManager.compileReport(jasperDesign);
        
        // Sencillo
        jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);  
        
//        // CON SUBREPORT
//        jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(SUBREPORT_JXML));
//        JasperReport subreport = JasperCompileManager.compileReport(jasperDesign);
//        HashMap params = new HashMap();
//        params.put("SUBREPORT", subreport);        
//        jasperPrint = JasperFillManager.fillReport(reporte, params, conexion);  
        
        
        // CON PARÁMETROS Y PRECOMPILADO
//        HashMap params = new HashMap();
//        params.put("value","hola");
//        reporte = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(REPORT));//new File(REPORT));
//        jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);        
    }
    

    public JasperReport getReporte() {
        return reporte;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }
    
public void setJasperPrint(String report) throws JRException{
    JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(report));
    this.reporte = JasperCompileManager.compileReport(jasperDesign);
}    
}
