package mess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class Person {

	String firstName = "";
	String lastName = "";
	String email = "";
	String country = "";
	ArrayList<String> dates;

	public Person(String first, String last, String email, String country, JSONArray dates)
			throws JSONException, ParseException {

		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.country = country;
		this.dates = convertDates(dates);

	}

	public static ArrayList<String> convertDates(JSONArray avaliable) throws JSONException, ParseException {
		ArrayList<String> dates = new ArrayList<String>();

		for (int i = 0; i < avaliable.length() - 1; i++) {

			String firstdate = avaliable.get(i).toString();
			String seconddate = avaliable.get(i + 1).toString();

			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(firstdate);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(seconddate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date2);

			// Adds one day to calendar to make both dates the same if they are consecutive
			calendar.add(Calendar.DATE, 1);

			if (DateUtils.isSameDay(calendar, calendar2)) {
				dates.add(firstdate);
			}
		}
		return dates;
	}

}
