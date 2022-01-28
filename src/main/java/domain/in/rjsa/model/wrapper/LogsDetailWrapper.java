package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.form.LogsJson;
import domain.in.rjsa.model.fy.Logs;
import lombok.Data;
@Data
public class LogsDetailWrapper {
	public Logs logs;
	public LogsJson logsjson;

}
