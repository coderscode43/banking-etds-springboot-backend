package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDao;
import domain.in.rjsa.model.ValidationMessages;
@Repository("validationMessagesDao")
public class ValidationMessagesDaoImpl extends AbstractDao<String, ValidationMessages> {

}
