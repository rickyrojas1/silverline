package mess;

import java.util.ArrayList;

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
