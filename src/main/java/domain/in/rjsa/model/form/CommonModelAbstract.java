package domain.in.rjsa.model.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class CommonModelAbstract<T extends Model> implements Model {
	/**
	 * 
	 */

	public String getFomrattedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

	public void setEntity(T entity) {

	}
	
	public Date returnDate(String dateAsString) throws ParseException {
		Date date = null;

		if (!(dateAsString.equals(""))) {
//			"Sep 2, 2024, 12:00:00 AM"
			String[] data = dateAsString.split(Pattern.quote(","), -1);
			String[] dayNmonth = data[0].split(Pattern.quote(" "), -1);

			String month = dayNmonth[0];
			String day = dayNmonth[1];
			String year = data[1];

			String formatttedDateAsString = year + "-" + getMonthNum(month) + "-"
					+ (day.length() == 1 ? "0" + day : day);

			return new SimpleDateFormat("yyyy-MM-dd").parse(formatttedDateAsString);
		} else {
			System.out.println("date save as null");
			return date;
		}
	}

	public String getMonthNum(String month) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Jan", "01");
		map.put("Feb", "02");
		map.put("Mar", "03");
		map.put("Apr", "04");
		map.put("May", "05");
		map.put("Jun", "06");
		map.put("Jul", "07");
		map.put("Aug", "08");
		map.put("Sep", "09");
		map.put("Oct", "10");
		map.put("Nov", "11");
		map.put("Dec", "12");

		return map.get(month);
	}
	
}
