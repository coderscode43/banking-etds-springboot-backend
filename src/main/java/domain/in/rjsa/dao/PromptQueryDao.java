package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

public interface PromptQueryDao {
	
	public List<HashMap<String, Object>> getData(String userText);
}
