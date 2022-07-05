package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.NoticeDao;
import domain.in.rjsa.model.form.Notice;

@Repository("NoticeDao")
public class NoticeDaoImpl  extends AbstractDaoForm<Long, Notice>  implements NoticeDao{

	@Override
	public List<Notice> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
