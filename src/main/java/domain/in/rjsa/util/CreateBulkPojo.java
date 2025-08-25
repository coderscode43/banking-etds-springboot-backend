package domain.in.rjsa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateBulkPojo {
	public static void main(String[] args) {
		try {
			String packageName = "package domain.in.rjsa"; // Package Header Name 
			
/* How to create excel for import? Excel format?
 * Create Sheet 1
 * In first column add all model/table names
 * In Second column add all columns name in given format which is available in table is given in first column  
 * format = ('coloumn1','coloumn2','coloumn3','coloumn4','coloumn5')
 * e.g. Model.xlsx
 * -----------------------------------------------------------------------   
 * |   A       |                             B                           |
 * -----------------------------------------------------------------------
 * | TableName |('coloumn1','coloumn2','coloumn3','coloumn4','coloumn5') |
 * -----------------------------------------------------------------------
 * 
 * */
// XXXXXXXXXXXXXXXXXXXXXXXXXX When Data import from Excel uncomment code XXXXXXXXXXXXXXXXXXXXXXX
			Workbook wb = null;
			FileInputStream fis = new FileInputStream("\\\\192.168.1.254\\ho\\Staff\\VAIBHAV\\Model.xlsx");
			// constructs an XSSFWorkbook object, by buffering the whole stream into the
			// memory
			wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0); // getting the XSSFSheet object at given index
			int i = 0;
			for (i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				String model = row.getCell(0).toString();
				String[] columns = row.getCell(1).toString().replace("`", "").split(Pattern.quote(","), -1);
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX			
			
//=========================== When Data import from Array list uncomment code ====================
//			String[] models = { "B2BACDNA", "B2BCDN", "ClientDetails", "Customers", "EinvoiceLogin",
//					"EinvoicePortalLogin", "FileReturnsList", "FileReturnsStatus", "Gstin", "GstinDetails", "GSTLogs",
//					"ImportDataLogs", "Invoice", "IRN", "ISD", "ISDA", "PAN", "Purchase", "PurchaseRegister",
//					"SellerDetails", "Vendors" };
//			for (String model : models) {
////===============================================================================================
				
				String path = "C:\\Users\\TAXO-TD-1163\\Desktop"; //Destination path for save the output files
				model(path, model, columns, packageName); //Uncomment when data import from excel
				service(path, model, packageName);
				serviceImpl(path, model, packageName);
				dao(path, model, packageName);
				daoImpl(path, model, packageName);
			}
			System.out.println("Done");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void model(String path, String model, String[] columns, String packageName) {
		try {
			File file = new File(path + "\\Model");
			if (!file.exists()) {
				file.mkdirs();
			}
			FileWriter myWriter = new FileWriter(file.getAbsolutePath() + "\\" + model + ".java");
			myWriter.write(packageName + ".model.GST;\r\n" + "\r\n" + "import javax.persistence.Column;\r\n"
					+ "import javax.persistence.Entity;\r\n" + "import javax.persistence.GeneratedValue;\r\n"
					+ "import javax.persistence.GenerationType;\r\n" + "import javax.persistence.Id;\r\n"
					+ "import javax.persistence.Table;\r\n" + "import org.hibernate.annotations.GenericGenerator;\r\n"
					+ "\r\n" + "import domain.in.rjsa.model.bankingGST.CommonModelAbstract;\r\n"
					+ "import lombok.Data;\r\n" + "\r\n" + "@Data\r\n" + "@Entity\r\n" + "@Table(name = \"AAACN4165C_form." + model
					+ "\", catalog = \"GST\")\r\n" + "public class " + model + " extends CommonModelAbstract<" + model
					+ ">{\r\n" + "\r\n" + "@Id\r\n"
					+ "@GeneratedValue(strategy = GenerationType.AUTO, generator = \"native\")\r\n"
					+ "@GenericGenerator(name = \"native\", strategy = \"native\")\r\n");
			for (String column : columns) {
				myWriter.write(
						"@Column(name = \"" + column.trim() + "\")\r\n" + "private String " + column.trim() + ";\r\n");
			}
			myWriter.write("\r\n" + "}\r\n");
			myWriter.close();

		} catch (

		Exception e) {
			// TODO: handle exception
		}
	}

	public static void service(String path, String model, String packageName) {
		try {
			File file = new File(path + "\\Service");
			if (!file.exists()) {
				file.mkdirs();
			}
			FileWriter myWriter = new FileWriter(file.getAbsolutePath() + "\\" + model + "Service.java");
			myWriter.write(packageName + ".service;\r\n" + "\r\n" + "import domain.in.rjsa.model.GST." + model + ";\r\n"
					+ "\r\n" + "public interface " + model + "Service extends ServiceInterface<Long, " + model
					+ ">{\r\n" + "\r\n" + "}\r\n");

			myWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void serviceImpl(String path, String model, String packageName) {
		try {
			File file = new File(path + "\\ServiceImpl");
			if (!file.exists()) {
				file.mkdirs();
			}
			FileWriter myWriter = new FileWriter(file.getAbsolutePath() + "\\" + model + "ServiceImpl.java");
			myWriter.write(packageName + ".service.impl;\r\n" + "\r\n"
					+ "import org.springframework.transaction.annotation.Transactional;\r\n" + "\r\n"
					+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
					+ "import org.springframework.stereotype.Service;\r\n" + "\r\n" + "import domain.in.rjsa.dao."
					+ model + "Dao;\r\n" + "import domain.in.rjsa.model.GST." + model + ";\r\n"
					+ "import domain.in.rjsa.service." + model + "Service;\r\n" + "@Transactional\r\n" + "@Service(\""
					+ model + "Service\")\r\n" + "public class " + model + "ServiceImpl extends AbstractService<Long, "
					+ model + ", " + model + "Dao> implements " + model + "Service {\r\n" + "\r\n" + " @Autowired\r\n"
					+ " " + model + "Dao dao;\r\n" + "\r\n" + "@Override\r\n" + "public " + model
					+ "Dao getPrimaryDao() {\r\n" + "	// TODO Auto-generated method stub\r\n" + "	return dao;\r\n"
					+ "    }\r\n" + "}\r\n");

			myWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void dao(String path, String model, String packageName) {
		try {
			File file = new File(path + "\\Dao");
			if (!file.exists()) {
				file.mkdirs();
			}
			FileWriter myWriter = new FileWriter(file.getAbsolutePath() + "\\" + model + "Dao.java");
			myWriter.write(packageName + ".dao;\r\n" + "\r\n" + "import org.springframework.stereotype.Repository;\r\n"
					+ "\r\n" + "import domain.in.rjsa.dao." + model + "Dao;\r\n" + "import domain.in.rjsa.model.GST."
					+ model + ";\r\n" + "public interface " + model + "Dao extends DaoInterface<Long, " + model
					+ ">{\r\n" + "\r\n" + "}\r\n");

			myWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void daoImpl(String path, String model, String packageName) {
		try {
			File file = new File(path + "\\DaoImpl");
			if (!file.exists()) {
				file.mkdirs();
			}
			FileWriter myWriter = new FileWriter(file.getAbsolutePath() + "\\" + model + "DaoImpl.java");
			myWriter.write(packageName + ".dao.impl;\r\n" + "\r\n"
					+ "import org.springframework.stereotype.Repository;\r\n" + "\r\n" + "import domain.in.rjsa.dao."
					+ model + "Dao;\r\n" + "import domain.in.rjsa.model.GST." + model + ";\r\n" + "@Repository(\""
					+ model + "Dao\")\r\n" + "public class " + model + "DaoImpl extends AbstractDao<Long, " + model
					+ "> implements " + model + "Dao {\r\n" + "\r\n" + "}\r\n");

			myWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
