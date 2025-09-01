package domain.in.rjsa.service;

import java.util.HashMap;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.wrapper.FileTypeWrapper;

public interface PromptQueryService {

	public String processRequest(HashMap<String, Object> data, FileTypeWrapper fileTypeWrapper) throws CustomException, Exception;

}
