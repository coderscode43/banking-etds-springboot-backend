package domain.in.rjsa.service;

import java.util.HashMap;

import domain.in.rjsa.exception.CustomException;

public interface PromptQueryService {

	public String processRequest(HashMap<String, Object> data) throws CustomException, Exception;

}
