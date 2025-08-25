package domain.in.rjsa.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.dao.RemarkDaoNew;
import domain.in.rjsa.model.form.Remark;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.RemarkServiceNew;

@Transactional
@Service("RemarkServiceNew")
public class RemarkServiceNewImpl extends AbstractServiceForm<Long, Remark, RemarkDaoNew> implements RemarkServiceNew {

	@Autowired
	RemarkDaoNew remarkDaoNew;
	
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		return null;
	}

	@Override
	public RemarkDaoNew getPrimaryDao() {
		return remarkDaoNew;
	}

	@Override
	public Remark searchForm(Long trackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remark> findTransaction(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		return remarkDaoNew.findTransaction(constrains, pageNo, noOfResult);
	}

	@Override
	public void addRemarkWithDocument(MultipartFile downloadFile, String principal,LinkedHashMap<String, String> lessonMap) throws IOException {
		
		
		
	}

	@Override
	public void addRemarkWithoutDocument(String principal, @Valid LinkedHashMap<String, String> entity) {
		
	}

	@Override
	public void updateRemarkWithFile(MultipartFile downloadFile, String principal,
			LinkedHashMap<String, String> lessonMap) throws IOException {
		
	}

	@Override
	public void updateRemarkWithoutDocument(String principal, @Valid LinkedHashMap<String, String> entity) {
		
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}


}
