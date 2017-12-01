package mess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Country {

	ArrayList<Person> allPartners = new ArrayList<>();
	ArrayList<Person> avaliable = new ArrayList<>();
	String bestDay = "";

	public void addPerson(Person p) {
		allPartners.add(p);

	}

	public void populate() {
		bestDay = getBestDate(allPartners);
		avaliable = findWhosAvaliable(allPartners, bestDay);
	}

	public String getBestDate(ArrayList<Person> partners) {
		Map<String, Integer> words = new TreeMap<String, Integer>();

		String bestDay = "";

		for (int i = 0; i < partners.size(); i++) {
			for (int k = 0; k < partners.get(i).dates.size(); k++) {
				String currentDate = partners.get(i).dates.get(k);

				// Checking if date is in Map. If it isn't we create it.
				// If date is in map we increment counter.

				if (!words.containsKey(partners.get(i).dates.get(k))) {
					words.put(currentDate, 1);
				} else {
					words.put(currentDate, words.get(currentDate) + 1);
				}

			}
		}

		bestDay = sortMapByValue(words);

		return bestDay;
	}

	public String sortMapByValue(Map mp) {

		// Sorts Map by Value and returns the key which will be the highest count.

		Entry maxEntry = Collections.max(mp.entrySet(), Map.Entry.comparingByValue());

		return maxEntry.getKey().toString();
	}

	public ArrayList<Person> findWhosAvaliable(ArrayList<Person> partners, String bestdate) {

		ArrayList<Person> avaliable = new ArrayList<Person>();

		for (int i = 0; i < partners.size(); i++) {
			ArrayList<String> l = partners.get(i).dates;
			if (l.contains(bestdate)) {
				avaliable.add(partners.get(i));

			}
		}

		return avaliable;

	}

}
