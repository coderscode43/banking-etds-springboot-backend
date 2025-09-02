package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.AddChallanDao;
import domain.in.rjsa.model.form.AddChallan;
 
@Repository("AddChallanDao")
public class AddChallanDaoImpl extends AbstractDaoForm<Long, AddChallan> implements AddChallanDao {

}
