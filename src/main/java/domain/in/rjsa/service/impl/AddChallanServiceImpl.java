package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.AddChallanDao;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.model.form.AddChallan;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.AddChallanService;
import domain.in.rjsa.util.StaticData;

@Transactional
@Service("AddChallanService")
public class AddChallanServiceImpl extends AbstractServiceForm<Long, AddChallan, AddChallanDao>
		implements AddChallanService {

	@Autowired
	AddChallanDao dao;

	@Autowired
	CorrectionRequestDao crDao;

	@Override
	public AddChallanDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void downloadDocument(Long id, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			HashMap<String, Object> map = new HashMap<>();
			CorrectionRequest cr = crDao.getByKey(id);
			map.put("correctionRequestId", id);
			AddChallan ad = dao.uniqueSearch(map);
			String date = cr.getCorrectionRequestDate().toString();
			String path = StaticData.documentSave;
			String date1 = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			String filePath = path + "//" + date1 + "//" + cr.getFy() + "//" + cr.getQuarter().replace(", ", "_") + "//"
					+ cr.getBranchCode() + "//CorrectionRequest//" + cr.getTicketNumber()
					+ "//challanSupportingDocument//" + ad.getChallanSupportingDocument();
			File file = new File(filePath);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				response.setStatus(HttpServletResponse.SC_OK);
				response.setHeader("Content-Disposition", " attachment; filename=" + ad.getChallanSupportingDocument());
				IOUtils.copy(fileInputStream, response.getOutputStream());
				fileInputStream.close();
			} else {
				filePath = path + "//" + date1 + "//" + cr.getFy() + "//" + cr.getBranchCode() + "//CorrectionRequest//"
						+ cr.getTicketNumber() + "//challanSupportingDocument//" + ad.getChallanSupportingDocument();
				file = new File(filePath);
				FileInputStream fileInputStream = new FileInputStream(file);
				response.setStatus(HttpServletResponse.SC_OK);
				response.setHeader("Content-Disposition", " attachment; filename=" + ad.getChallanSupportingDocument());
				IOUtils.copy(fileInputStream, response.getOutputStream());
				fileInputStream.close();

			}
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public AddChallan getByCorrectionId(Long correctionRequestId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("correctionRequestId", correctionRequestId);
		return dao.uniqueSearch(map);
	}
}
