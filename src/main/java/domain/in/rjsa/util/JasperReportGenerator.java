package domain.in.rjsa.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

public class JasperReportGenerator {

	static final Logger logger = LoggerFactory.getLogger(JasperReportGenerator.class);
	public  static String path ;
	Map<String, Object> jasperParameters;
	public String pdfFile;
	public void setPath(){
		File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
		
		try {
			path = myClass.getCanonicalPath().split("WEB-INF")[0];
		} catch (IOException e1) {
		
			logger.info("Error in setting the path ",e1);
		}
		
	}

	 public  JasperReportGenerator(Map<String, Object> jasperParameters,String type) {
		this.jasperParameters=jasperParameters;
		if(path == null || path.isEmpty()){
			setPath();
		}
		String jasperFile=path + "static/report/"+type+".jasper";
		try {
			this.pdfFile=path + "static/report/"+jasperParameters.get("Name")+".pdf";
			JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReport(jasperFile, jasperParameters,new JREmptyDataSource()), this.pdfFile);
		} catch (JRException e) {
			this.pdfFile="";
			logger.info("Error in generating pdf",e);
		}
	}
}
