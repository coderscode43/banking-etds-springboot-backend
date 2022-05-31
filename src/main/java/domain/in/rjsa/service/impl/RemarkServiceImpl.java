
package domain.in.rjsa.service.impl;


import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.dao.Regular27QDeducteeDao;
import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.RemarkService;

@Transactional("transactionManager")
@Service("remarkService")
public class RemarkServiceImpl extends AbstractServiceFY<Long, Remark,RemarkDao> implements RemarkService{
	
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
	
	@Override
	public RemarkDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public void saveRemark(LinkedHashMap<String, Object> entity){
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		getPrimaryDao().persist(gson.fromJson(jsonElement, getEntity()));
	}
	
	public Class<Remark> getEntity() {
		// TODO Auto-generated method stub
		return Remark.class;
	}

	@Override
	public void setResolve(Remark remark, String type) {
		save(remark);
		Long ID =  remark.getDeducteeId();
		Long id = new Long(ID);
		String form = remark.getDeducteeForm();
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
				Ticket ticket = tDao.getByKey(id);
				ticket.setResolved(true);
				ticket.setStatus("Open");
				tDao.update(ticket);
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
				Ticket ticket = tDao.getByKey(id);
				ticket.setResolved(false);
				ticket.setStatus(type);
				tDao.update(ticket);
			}
		}
		
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
	
}
