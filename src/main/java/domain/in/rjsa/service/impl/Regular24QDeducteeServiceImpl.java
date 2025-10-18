package domain.in.rjsa.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.ChallanDetailsDao;
import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.dto.TDSAmountDto;
import domain.in.rjsa.excel.Form24QDeducteeExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.ChallanDetails;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular24QDeducteeService;

@Transactional("transactionManager")
@Service("regular24QDeducteeService")
public class Regular24QDeducteeServiceImpl extends AbstractServiceFY<Long, Regular24QDeductee, Regular24QDeducteeDao>
		implements Regular24QDeducteeService {

	@Autowired
	Regular24QDeducteeDao dao;
	
	@Autowired
	RemarkDao rDao;
	
	@Autowired
	ChallanDetailsDao challanDetailsDao;

	Form24QDeducteeExcel form24QDeduceteeExcel;
	public static String path;
	public String ExcelFile;

	@Override
	public Regular24QDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public Regular24QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	

	@Override
	public List<String> getPanList(String s, String fy, Long branchCode) {
		return dao.getPanList(s,fy,branchCode);
	}

	
	public void update(Regular24QDeductee entity) {
		// TODO Auto-generated method stub

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		Object id = entity.getId();
//		Regular24QDeductee regular24QWeb = new Regular24QDeductee();//from Gson //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		map.put("id", id);
		Regular24QDeductee regular24Q = dao.uniqueSearch(map);
		regular24Q.updateAllowedFields(entity);
		getPrimaryDao().update(regular24Q);
//		HashMap<String, Object> m = new HashMap<>();
//		m.put("deducteeId", entity.getId());
//		m.put("deducteeForm", "24QForm");
//		List<Remark> remark = rDao.findall(m, 0, 100);
//		for(Remark r : remark) {
//			r.setBranchCode(entity.getBranchCode());
//			rDao.persist(r);
//		}
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Regular24QDeductee> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		form24QDeduceteeExcel = new Form24QDeducteeExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		form24QDeduceteeExcel.initialise(path + "static/report/TDS-" + timestamp + "-24QDeductee.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-24QDeductee.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = form24QDeduceteeExcel.getWorkbook();
		Sheet form24QDeductee = wb.getSheet("form24QDeductee-" + part);
		for (Regular24QDeductee form24Q : listUsers) {

			Row details = form24QDeductee.createRow(row);
			details.createCell(0).setCellValue(row);

			if (form24Q.getQuarter() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(form24Q.getQuarter());
			}
			if (form24Q.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(form24Q.getMonth());
			}
			if (form24Q.getRoCode() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(form24Q.getRoCode());
			}
			if (form24Q.getBranchCode() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(form24Q.getBranchCode());
			}
			if (form24Q.getChallanHeading() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(form24Q.getChallanHeading());
			}
			if (form24Q.getPan()== null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(form24Q.getPan());
			}
			if (form24Q.getName() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(form24Q.getName());
			}
			if (form24Q.getSectionCode() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(form24Q.getSectionCode());
			}
			if (form24Q.getTAN() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(form24Q.getTAN());
			}
			if (form24Q.getUniqueRefNo() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(form24Q.getUniqueRefNo());
			}
			if (form24Q.getAccNo() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(form24Q.getAccNo());
			}
			if (form24Q.getDateOfPayment() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form24Q.getDateOfPayment()));
			}
			if (form24Q.getDateOfDeduction() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form24Q.getDateOfDeduction()));
			}
			if (form24Q.getAmountPaid() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14).setCellValue(form24Q.getAmountPaid());
			}
			if (form24Q.getTds() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(form24Q.getTds());
			}
			if (form24Q.getSurcharge() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(form24Q.getSurcharge());
			}
			if (form24Q.getEduCess() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(form24Q.getEduCess());
			}
			if (form24Q.getTotalTaxDeducted() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(form24Q.getTotalTaxDeducted());
			}
			if (form24Q.getTotalTaxDeposited() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(form24Q.getTotalTaxDeposited());
			}
			if (form24Q.getCertificateNumber() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(form24Q.getCertificateNumber());
			}
			if (form24Q.getRemarksReason() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(form24Q.getRemarksReason());
			}
			if (form24Q.getPanRefNo() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(form24Q.getPanRefNo());
			}
			if (form24Q.getErrorDescription() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(form24Q.getErrorDescription());
			}
			if (form24Q.getWarningDescription() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(form24Q.getWarningDescription());
			}
			if (form24Q.getShortDeduction() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(form24Q.getShortDeduction());
			}
			if (form24Q.getInterestOnShortDeduction() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(form24Q.getInterestOnShortDeduction());
			}
			if (form24Q.getInterestOnLatePayment() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(form24Q.getInterestOnLatePayment());
			}
			if (form24Q.getInterestOnLateDeduction() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(form24Q.getInterestOnLateDeduction());
			}
			if (form24Q.getComments() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29).setCellValue(form24Q.getComments());
			}
			if (form24Q.isResolved()) {
				details.createCell(30).setCellValue("Not Resolved");
			} else {
				details.createCell(30).setCellValue("Resolved");
			}

			if (row > 1000000) {
				part++;
				wb = form24QDeduceteeExcel.getWorkbook();
				form24QDeductee = form24QDeduceteeExcel.initializeSheet("form24QDeductee-" + part);
				row = 0;
			}
			row++;

		}
		form24QDeduceteeExcel.close();
		return ExcelFile;

	}

	public void setPath() {
		File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

		try {
			path = myClass.getCanonicalPath().split("WEB-INF")[0];
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findallCount(constrains);
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject =  mapper.readValue(json, new TypeReference <List<JSONObject>>() {
			});
			
			for (JSONObject object : jsonObject) {
				Regular24QDeductee deductee = new Regular24QDeductee();
				deductee.setData(object);
				dao.persist(deductee);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}
	
	@Override
	public byte[] getAmountDetailsAsExcel(String quarter) {
		List<TDSAmountDto> tdsAmountList = dao.getAmountDetails(quarter);

		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("excel/TDSAmount.xlsx");
				Workbook wb = new XSSFWorkbook(stream);
				ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			Sheet sheet = wb.getSheet("TDS-1");

			int srNo = 1;
			int row = 1;

			for (TDSAmountDto tdsAmount : tdsAmountList) {
				Row rowNum = sheet.createRow(row);

				int cell = 0;
				Cell cellObj;
				rowNum.createCell(cell++).setCellValue(srNo);

				cellObj = rowNum.createCell(cell++);
				if (StringUtils.hasText(tdsAmount.getTan())) {
					cellObj.setCellValue(tdsAmount.getTan());
				}

				cellObj = rowNum.createCell(cell++);
				cellObj.setCellValue(quarter);

				cellObj = rowNum.createCell(cell++);
				if (tdsAmount.getAmount() == null) {
					cellObj.setCellValue(0.00);
				} else {
					cellObj.setCellValue(tdsAmount.getAmount());
				}

				row++;
				srNo++;
			}

			wb.write(baos);
			return baos.toByteArray();

		} catch (Exception e) {
			throw new CustomException("Error generating amout excel.");
		}
	}

	@Override
	public void mapChallan(HashMap<String, Object> data) {
		List<Regular24QDeductee> deductees = dao.getDetails(data);

		if (deductees == null || deductees.isEmpty()) {
			return;
		}

		double maxAmount = Double.parseDouble(data.get("amount").toString());
		double currentSum = 0.0;

		String fy = deductees.get(0).getFy();
		ChallanDetails currentChallan = createNewChallan(data, fy);

		for (Regular24QDeductee deductee : deductees) {
			double remainingTds = deductee.getTds();
			boolean isFirstPart = true;

			while (remainingTds > 0) {
				double spaceLeft = maxAmount - currentSum;
				double amountToAdd = Math.min(remainingTds, spaceLeft);

				Regular24QDeductee deducteePart;

				if (isFirstPart) {
					deductee.setTds(amountToAdd);
					deducteePart = deductee;
					deducteePart.setChallanDetails(currentChallan);
					dao.update(deducteePart);

					isFirstPart = false;
				} else {
					deducteePart = createNewDeductee(deductee, amountToAdd);
					deducteePart.setChallanDetails(currentChallan);
					dao.persist(deducteePart);
				}

				currentSum += amountToAdd;
				remainingTds -= amountToAdd;

				if (currentSum == maxAmount) {
					currentChallan.setTds(currentSum);
					challanDetailsDao.persist(currentChallan);

					currentChallan = createNewChallan(data, fy);
					currentSum = 0.0;
				}
			}
		}

		if (currentSum > 0.0) {
			currentChallan.setTds(currentSum);
			challanDetailsDao.persist(currentChallan);
		}

	}

	public ChallanDetails createNewChallan(HashMap<String, Object> data, String fy) {
		ChallanDetails challanDetails = new ChallanDetails();
		String tan = data.get("TAN").toString().split("-")[0];
		challanDetails.setTAN(tan);
		challanDetails.setFy(fy);
		challanDetails.setMonth(data.get("month").toString());
		challanDetails.setTds(0.0);
		return challanDetails;
	}

	private Regular24QDeductee createNewDeductee(Regular24QDeductee original, Double newTds) {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.convertValue(original, new TypeReference<HashMap<String, Object>>() {
		});

		Regular24QDeductee newDeductee = mapper.convertValue(map, Regular24QDeductee.class);
		newDeductee.setId(null);
		newDeductee.setTds(newTds);

		return newDeductee;
	}

}
