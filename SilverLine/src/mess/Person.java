package mess;

<<<<<<< HEAD
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
=======
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class Person {

	String firstName = "";
	String lastName = "";
	String email = "";
	String country = "";
	ArrayList<String> dates;

	public Person(String first, String last, String email, String country, JSONArray dates) throws JSONException {
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.country = country;

		this.dates = convertDates(dates);

	}

	public static ArrayList<String> convertDates(JSONArray avaliable) throws JSONException {
		ArrayList<String> dates = new ArrayList<String>();
		int aprilEnd = 30;
		int mayEnd = 31;
		int juneEnd = 30;
		int julyEnd = 31;

		for (int i = 0; i < avaliable.length() - 1; i++) {

			String firstdate = avaliable.get(i).toString();
			String seconddate = avaliable.get(i + 1).toString();

			String[] date1 = firstdate.split("-");
			String[] date2 = seconddate.split("-");

			int firstday = Integer.parseInt(date1[2]);
			int secondday = Integer.parseInt(date2[2]);
			int firstMonth = Integer.parseInt(date1[1]);
			int secondMonth = Integer.parseInt(date2[1]);

			if (secondday == firstday + 1) {
				dates.add(firstdate);
			} else if ((firstMonth == 4) && (firstday == aprilEnd) && (secondMonth == 5) && (secondday == 1)) {
				dates.add(firstdate);

			} else if ((firstMonth == 5) && (firstday == mayEnd) && (secondMonth == 6) && (secondday == 1)) {
				dates.add(firstdate);

			} else if ((firstMonth == 6) && (firstday == juneEnd) && (secondMonth == 7) && (secondday == 1)) {
				dates.add(firstdate);

			} else if ((firstMonth == 7) && (firstday == julyEnd) && (secondMonth == 8) && (secondday == 1)) {
				dates.add(firstdate);

>>>>>>> refs/remotes/origin/master
			}

		}
		return dates;
	}

}
