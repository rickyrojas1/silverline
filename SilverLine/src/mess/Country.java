package mess;

import java.util.ArrayList;
<<<<<<< HEAD
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
=======

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
		ArrayList<String> all = new ArrayList<String>();
		ArrayList<String> possibilities = new ArrayList<String>();

		String bestDay = "";

		for (int i = 0; i < partners.size(); i++) {
			for (int k = 0; k < partners.get(i).dates.size(); k++) {
				all.add(partners.get(i).dates.get(k));
				if (!possibilities.contains(partners.get(i).dates.get(k))) {
					possibilities.add(partners.get(i).dates.get(k));
				}

			}
		}

		bestDay = mostCommon(all, possibilities);
		return bestDay;
	}

	public String mostCommon(ArrayList<String> all, ArrayList<String> possibilities) {
		int newcount = 0;
		int oldcount = 0;
		String mostCommon = "";

		for (int i = 0; i < possibilities.size(); i++) {
			for (int k = 0; k < all.size(); k++) {
				if (possibilities.get(i).equals(all.get(k))) {
					newcount++;
				}
			}
			if (newcount > oldcount) {
				oldcount = newcount;
				mostCommon = possibilities.get(i);
			}
			newcount = 0;
		}
		return mostCommon;
>>>>>>> refs/remotes/origin/master

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
