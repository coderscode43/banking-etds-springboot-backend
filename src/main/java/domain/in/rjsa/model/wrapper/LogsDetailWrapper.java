package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.fy.LogsJson;
import lombok.Data;
@Data
public class LogsDetailWrapper {
	public Logs logs;
	public LogsJson logsjson;

}
