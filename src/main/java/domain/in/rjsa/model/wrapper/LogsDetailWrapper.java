package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.form.Logs;
import domain.in.rjsa.model.form.LogsJson;
import lombok.Data;
@Data
public class LogsDetailWrapper {
	public Logs logs;
	public LogsJson logsjson;

}
