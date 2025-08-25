
package domain.in.rjsa.service.impl;


import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.dao.Regular27QDeducteeDao;
import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.model.fy.Tickets;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.RemarkService;

@Transactional("transactionManager")
@Service("remarkService")
public class RemarkServiceImpl extends AbstractServiceFY<Long, Remarks,RemarkDao> implements RemarkService{
	
	@Autowired
	RemarkDao dao;
	@Autowired
	Regular24QDeducteeDao r24qDao;
	@Autowired
	Regular26QDeducteeDao r26qDao;
	@Autowired
	Regular27EQDeducteeDao r27eqDao;
	@Autowired
	Regular27QDeducteeDao r27qDao;
	@Autowired
	TicketDao tDao;
	
	@Autowired
	StaticDataDao sDao;

	Tickets t = new Tickets();
	
	@Override
	public RemarkDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public void saveRemark(LinkedHashMap<String, Object> entity){
		// TODO Auto-generated method stub
		try {
			String jsonElement = mapper.writeValueAsString(entity);
			getPrimaryDao().persist(mapper.readValue(jsonElement, getEntity()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Class<Remarks> getEntity() {
		// TODO Auto-generated method stub
		return Remarks.class;
	}

	@Override
	public void setResolve(Remarks remarks, String type) {
		save(remarks);
		Long ID =  remarks.getDeducteeId();
		Long id = new Long(ID);
		String form = remarks.getDeducteeForm();
		if (type.endsWith("Remark")) {
			if (form.endsWith("24Qform")) {
				Regular24QDeductee r24q = r24qDao.getByKey(id);
				r24q.setResolved(true);
				r24qDao.update(r24q);
			} else if (form.endsWith("26Qform")) {
				Regular26QDeductee r26q = r26qDao.getByKey(id);
				r26q.setResolved(true);
				r26qDao.update(r26q);
			} else if (form.endsWith("27EQform")) {
				Regular27EQDeductee r27eq = r27eqDao.getByKey(id);
				r27eq.setResolved(true);
				r27eqDao.update(r27eq);
			} else if (form.endsWith("27Qform")) {
				Regular27QDeductee r27q = r27qDao.getByKey(id);
				r27q.setResolved(true);
				r27qDao.update(r27q);
			} else if (form.endsWith("ticket")) {
				Tickets tickets = tDao.getByKey(id);
				tickets.setResolved(true);
				tickets.setStatus("Open");
				tDao.update(tickets);
			}
		}else if(type.endsWith("Resolve")||type.endsWith("Reject"))
		{	
			if (form.endsWith("24Qform")) {
				Regular24QDeductee r24q = r24qDao.getByKey(id);
				r24q.setResolved(false);
				r24qDao.update(r24q);
			} else if (form.endsWith("26Qform")) {
				Regular26QDeductee r26q = r26qDao.getByKey(id);
				r26q.setResolved(false);
				r26qDao.update(r26q);
			} else if (form.endsWith("27EQform")) {
				Regular27EQDeductee r27eq = r27eqDao.getByKey(id);
				r27eq.setResolved(false);
				r27eqDao.update(r27eq);
			} else if (form.endsWith("27Qform")) {
				Regular27QDeductee r27q = r27qDao.getByKey(id);
				r27q.setResolved(false);
				r27qDao.update(r27q);
			} else if (form.endsWith("ticket")) {
				Tickets tickets = tDao.getByKey(id);
				tickets.setResolved(false);
				tickets.setStatus(type);
				tDao.update(tickets);
			}
		}
		
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}


	@Override
	public void SaveRemarkWithDocument(MultipartFile downloadFile, Long branchCode, String deducteeForm, String remark,
			String principal, Long deducteeId, String fy,String quarter,String form) {
		try {
			Remarks r = new Remarks();
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			r.setBranchCode(branchCode);
			r.setDeducteeId(deducteeId);
			r.setRemark(remark);
			r.setFileName(downloadFile.getOriginalFilename());
			r.setDateTime(timeStamp);
			r.setDeducteeForm(deducteeForm);
			r.setFy(fy);
			r.setStatus("Remark");
			r.setUserName(principal);
			dao.persist(r);
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			for (StaticDataModel l : list) {
				if (l.getKey().equalsIgnoreCase("documentSave")) {
					String path = l.getValue();
					String filepath = path + "//" + date + "//" + fy + "//" + quarter + "//" +
							 branchCode + "//Ticket//" + form + "//" + downloadFile.getOriginalFilename();
					File file = new File(filepath.toString());
					if (!file.exists()) {
						file.mkdirs();
					}
					downloadFile.transferTo(file);
				}
			}
			setResolve(r, "Remark");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		@Override
		public void downloadDocument(Long id, HttpServletResponse response) {
			try {
				Remarks r = dao.getByKey(id);
				Tickets t = tDao.getByKey(r.getDeducteeId());
				String form = t.getForm();
				String quarter = t.getQuarter();
				HashMap<String, Object> sd = new HashMap<String, Object>();
				List<StaticDataModel> list = sDao.findall(sd, 0, 100);
				for (StaticDataModel l : list) {
					if (l.getKey().equalsIgnoreCase("documentSave")) {
						String path = l.getValue();
						String date = r.getDateTime().split(Pattern.quote("T"), -1)[0];
//						String filePath = path + "//" + date + "//" + r.branchCode + "//" + r.getDeducteeId()
//								+ "//" + r.getFileName();
						
						String filePath =  path + "//" + date + "//" + r.fy + "//" + quarter + "//" +
								r.branchCode + "//Ticket//" + form + "//" + r.getFileName();
						File file = new File(filePath);
						byte[] fileContent;
						fileContent = Files.readAllBytes(file.toPath());
						String mimeType = URLConnection.guessContentTypeFromName(r.getFileName());
						response.setContentType(mimeType);
						response.setStatus(HttpServletResponse.SC_OK);
						response.setHeader("Content-Disposition", " attachment; filename=" + r.getFileName());
						response.getOutputStream().write(fileContent);
						response.getOutputStream().close();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void addData(String json) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

