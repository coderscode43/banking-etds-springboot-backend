package domain.in.rjsa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Notice;
import domain.in.rjsa.model.form.Remark;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.service.NoticeService;
import domain.in.rjsa.service.RemarkServiceNew;

@Controller
@RequestMapping("/apiNotice")
public class NoticeController  extends AbstractControllerForm<Long, Notice, NoticeService>{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	NoticeService service;
	
	@Autowired
	RemarkServiceNew remrkServiceNew; 
	
	@Override
	public NoticeService getService() {
		// TODO Auto-generated method stub
		return service;
	}
	@Override
	public Class<Notice> getEntity() {
		// TODO Auto-generated method stub
		return Notice.class;
	}

	
	/*-------------------List of Shareholder on Main.html-------------------*/
	@RequestMapping(value = "/list/count", method = RequestMethod.GET)
	public ResponseEntity<?> count(HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<String, Object>();
		try {
			Long count = service.findallCount(constrains);
			List<?> list = getList(0, 10);
			ListCount send = new ListCount();
			send.setEntities(list);
			send.setCount(count);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public List<?> getList(int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<String, Object>();
		return service.findAll(constrains,pageNo, resultPerPage);
	}
	
	/*-------------------get Notice Detail and List of Remarks in remarks.html-------------------*/
	@RequestMapping(value = "/detail/{id}/{din}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id,@PathVariable String din ) {
		try {
			return new ResponseEntity<Object>(getDetail(id,din), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	public HashMap<String, Object> getDetail(Long id,String din) {
		
		List<Remark> listRemark = new ArrayList<Remark>();
		
		Notice notice = new Notice();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		//get single notice object based on id
		map.put("id", id);
		notice = service.uniqueSearch(map);
		data.put("Notice", notice);
		
		map.remove("id");
		
		//get List of remark object based on din
		map.put("DIN", din);
		listRemark = remrkServiceNew.search(map);
		data.put("remarkList", listRemark);
		
		return data;
	}
	
	
		
}
