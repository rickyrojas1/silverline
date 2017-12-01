package mess;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
<<<<<<< HEAD
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SilverLine {
	static MainFrame f;

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				f = new MainFrame("Company Partner Summit!");
				f.setLayout(new BorderLayout());
				f.setSize(1100, 600);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setResizable(false);
				f.setVisible(true);

				try {
					runProgram();
				} catch (JSONException | ParseException e) {
					e.printStackTrace();
				}

			}
		});

	}

	public static void runProgram() throws JSONException, ParseException {
		ArrayList<Person> all = new ArrayList<>();
		Map<String, Country> countries = new HashMap<String, Country>();

		JSONArray partners = getApi();

		for (int i = 0; i < partners.length(); i++) {

			JSONObject myresponse = new JSONObject(partners.get(i).toString());
			String firstname = myresponse.getString("firstName");
			String lastname = myresponse.getString("lastName");
			String email = myresponse.getString("email");
			String country = myresponse.getString("country");

			JSONArray date_list = new JSONArray(myresponse.getJSONArray("availableDates").toString());
			Person temp = new Person(firstname, lastname, email, country, date_list);
			all.add(temp);
			MainFrame.addPartners(temp);

			// If Country is not already a Key in the Map we will create and add it
			// If Country is already a Key in the Map we will create and add it

			if (!countries.containsKey(country)) {
				Country c = new Country();
				c.addPerson(temp);
				countries.put(country, c);
				MainFrame.radio.addItem(country);
			} else {
				countries.get(country).addPerson(temp);
			}

		}
		populateCountries(countries);
		MainFrame.transferPartners(countries);

	}

	public static JSONArray getApi() {
		try {

			String url = "https://candidate.hubteam.com/candidateTest/v2/partners?userKey=a39debefb0c5e465581205829678";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject myresponse = new JSONObject(response.toString());
			JSONArray partner = myresponse.getJSONArray("partners");

			return partner;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// ITERATES THROUGH EACH COUNTRY AND CALLS THE COUNTRY'S POPULATE FUNCTION.
	// Iterates through each Country and calls the Country's Populate function.

	public static void populateCountries(Map mp) {
		Iterator<Map.Entry<String, Country>> iterator = mp.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Country> entry = iterator.next();
			entry.getValue().populate();
		}
=======
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SilverLine {
	static MainFrame f;

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				f = new MainFrame("Company Partner Summit!");

				f.setLayout(new BorderLayout());
				f.setSize(1100, 600);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setResizable(false);
				f.setVisible(true);

				try {
					runProgram();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	public static void runProgram() throws JSONException {
		ArrayList<Person> all = new ArrayList<>();
		Country francePartners = new Country();
		Country usPartners = new Country();
		Country ukPartners = new Country();
		Country spainPartners = new Country();
		Country irelandPartners = new Country();
		Country japanPartners = new Country();
		Country canadaPartners = new Country();
		Country mexicoPartners = new Country();
		Country singaporePartners = new Country();

		JSONArray partners = getApi();

		for (int i = 0; i < partners.length(); i++) {

			JSONObject myresponse = new JSONObject(partners.get(i).toString());
			String firstname = myresponse.getString("firstName");
			String lastname = myresponse.getString("lastName");
			String email = myresponse.getString("email");
			String country = myresponse.getString("country");

			JSONArray date_list = new JSONArray(myresponse.getJSONArray("availableDates").toString());
			Person temp = new Person(firstname, lastname, email, country, date_list);
			all.add(temp);
			MainFrame.addPartners(temp);

			if (country.equals("France")) {
				francePartners.addPerson(temp);
			}
			if (country.equals("United States")) {
				usPartners.addPerson(temp);
			}
			if (country.equals("United Kingdom")) {
				ukPartners.addPerson(temp);
			}
			if (country.equals("Ireland")) {
				irelandPartners.addPerson(temp);
			}
			if (country.equals("Canada")) {
				canadaPartners.addPerson(temp);
			}
			if (country.equals("Mexico")) {
				mexicoPartners.addPerson(temp);
			}
			if (country.equals("Japan")) {
				japanPartners.addPerson(temp);
			}
			if (country.equals("Spain")) {
				spainPartners.addPerson(temp);
			}
			if (country.equals("Singapore")) {
				singaporePartners.addPerson(temp);
			}
		}

		francePartners.populate();
		usPartners.populate();
		ukPartners.populate();
		spainPartners.populate();
		irelandPartners.populate();
		japanPartners.populate();
		canadaPartners.populate();
		mexicoPartners.populate();
		singaporePartners.populate();

		MainFrame.transferPartners(francePartners, usPartners, ukPartners, japanPartners, mexicoPartners,
				singaporePartners, canadaPartners, spainPartners, irelandPartners, all);

	}

	public static JSONArray getApi() {
		try {

			String url = "https://candidate.hubteam.com/candidateTest/v2/partners?userKey=a39debefb0c5e465581205829678";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject myresponse = new JSONObject(response.toString());

			JSONArray partner = myresponse.getJSONArray("partners");

			System.out.println(partner);
			return partner;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
>>>>>>> refs/remotes/origin/master
	}

}
