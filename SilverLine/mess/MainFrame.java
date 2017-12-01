package mess;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	static JTable table;
	static int startPoint = 0;
	static Map<String, Country> countries = new HashMap<String, Country>();
	static Map<String, String> flags = new HashMap<String, String>();

	static JComboBox<String> radio;

	public MainFrame(String title) {
		super(title);

		String[] columns = { "First Name", "Last Name", "Email", "Country", "Avaliable Dates" };
		String[][] data = { {} };

		table = new JTable(new DefaultTableModel(data, columns));

		JButton button = new JButton("View all partners!");
		JButton button2 = new JButton("View Attending Partners");
		JLabel selectLabel = new JLabel("Select Country:");
		JLabel flag = new JLabel("");
		JLabel attendence = new JLabel("to view Partners");
		JLabel attendence2 = new JLabel("");
		JLabel date = new JLabel("");
		JLabel countryConference = new JLabel("         Select a country ");
		JLabel partnerLabel = new JLabel("All Partners");
		JScrollPane js = new JScrollPane(table);
		radio = new JComboBox<String>();

		selectLabel.setFont(new Font("Serif", Font.BOLD, 22));
		attendence.setFont(new Font("Serif", Font.BOLD, 22));
		attendence2.setFont(new Font("TimesRoman", Font.BOLD, 22));
		date.setFont(new Font("Serif", Font.BOLD, 22));
		countryConference.setFont(new Font("TimesRoman", Font.BOLD, 20));
		partnerLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));

		button.setBounds(910, 480, 170, 60);
		button2.setBounds(740, 480, 170, 60);
		radio.setBounds(290, 472, 150, 60);
		selectLabel.setBounds(140, 470, 150, 60);
		partnerLabel.setBounds(800, 10, 400, 100);
		attendence.setBounds(820, 285, 300, 163);
		attendence2.setBounds(765, 310, 300, 163);
		date.setBounds(840, 225, 300, 163);
		countryConference.setBounds(780, 200, 300, 163);
		flag.setBounds(750, 85, 300, 163);
		js.setBounds(35, 40, 700, 400);

		// Mapping Countries with their flag pictures

		flags.put("Canada", "/src/res/canada.jpg");
		flags.put("United States", "/src/res/usflag.png");
		flags.put("United Kingdom", "/src/res/ukflag.jpg");
		flags.put("France", "/src/res/france.jpg");
		flags.put("Mexico", "/src/res/mexico.jpg");
		flags.put("Japan", "/src/res/japan.jpg");
		flags.put("Ireland", "/src/res/ireland.jpg");
		flags.put("Singapore", "/src/res/singapore.jpg");
		flags.put("Spain", "/src/res/spain.jpg");
		flags.put("All", "/src/res/silverlineflag.jpg");

		flag.setIcon(new javax.swing.ImageIcon(getClass().getResource(flags.get("All"))));

		radio.addItem("All");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				String current = radio.getSelectedItem().toString();

				if (current.equals("All")) {

					// Iterates through the map of Countries and adds all Partners to Table

					Iterator<Map.Entry<String, Country>> it = countries.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, Country> pair = it.next();

						for (int k = 0; k < pair.getValue().allPartners.size(); k++) {
							model.addRow(new Object[] { pair.getValue().allPartners.get(k).firstName,
									pair.getValue().allPartners.get(k).lastName,
									pair.getValue().allPartners.get(k).email,
									pair.getValue().allPartners.get(k).country,
									pair.getValue().allPartners.get(k).dates, });
						}
					}
				} else {
					for (int i = 0; i < countries.get(current).allPartners.size(); i++) {

						// Iterates through the selected Country's Partners and adds them to the Table.

						model.addRow(new Object[] { countries.get(current).allPartners.get(i).firstName,
								countries.get(current).allPartners.get(i).lastName,
								countries.get(current).allPartners.get(i).email,
								countries.get(current).allPartners.get(i).country,
								countries.get(current).allPartners.get(i).dates, });
					}

					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource(flags.get(current))));
					partnerLabel.setText(current + " Partners");
					countryConference.setText(current + " Conference will be: ");
					date.setText(countries.get(current).bestDay);
					attendence.setText(countries.get(current).avaliable.size() + " out of the "
							+ countries.get(current).allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}
			}

		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				String current = radio.getSelectedItem().toString();

				if (current.equals("All")) {

					// Iterates through the map of Countries and adds all available Partners to
					// Table

					Iterator<Map.Entry<String, Country>> it = countries.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, Country> pair = it.next();

						for (int k = 0; k < pair.getValue().avaliable.size(); k++) {
							model.addRow(new Object[] { pair.getValue().avaliable.get(k).firstName,
									pair.getValue().avaliable.get(k).lastName, pair.getValue().avaliable.get(k).email,
									pair.getValue().avaliable.get(k).country,
									pair.getValue().avaliable.get(k).dates, });
						}
					}
				} else {
					// Iterates through the selected Country's available Partners and adds them to
					// the Table.

					for (int i = 0; i < countries.get(current).avaliable.size(); i++) {

						model.addRow(new Object[] { countries.get(current).avaliable.get(i).firstName,
								countries.get(current).avaliable.get(i).lastName,
								countries.get(current).avaliable.get(i).email,
								countries.get(current).avaliable.get(i).country,
								countries.get(current).avaliable.get(i).dates, });
					}

					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource(flags.get(current))));
					partnerLabel.setText(current + " Partners");
					countryConference.setText(current + " Conference will be: ");
					date.setText(countries.get(current).bestDay);
					attendence.setText(countries.get(current).avaliable.size() + " out of the "
							+ countries.get(current).allPartners.size());
					attendence2.setText("Partners will be in attendence!");
				}
			}

		});

		radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int avaliable = 0;
				int total = 0;
				JComboBox combo = (JComboBox) e.getSource();
				String current = (String) combo.getSelectedItem();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				if (current.equals("All")) {

					Iterator<Map.Entry<String, Country>> it = countries.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, Country> pair = it.next();

						for (int k = 0; k < pair.getValue().allPartners.size(); k++) {
							model.addRow(new Object[] { pair.getValue().allPartners.get(k).firstName,
									pair.getValue().allPartners.get(k).lastName,
									pair.getValue().allPartners.get(k).email,
									pair.getValue().allPartners.get(k).country,
									pair.getValue().allPartners.get(k).dates, });
							total++;
						}
						for (int k = 0; k < pair.getValue().avaliable.size(); k++) {
							avaliable++;
						}
						flag.setIcon(new javax.swing.ImageIcon(getClass().getResource(flags.get(current))));
						partnerLabel.setText("All Partners");
						countryConference.setText("     We currently have: ");
						attendence.setText(avaliable + " out of the " + total);
						attendence2.setText("Partners will be in attendence!");
						date.setText("");
						partnerLabel.setBounds(800, 10, 400, 100);
					}

				} else {

					for (int i = 0; i < countries.get(current).allPartners.size(); i++) {

						model.addRow(new Object[] { countries.get(current).allPartners.get(i).firstName,
								countries.get(current).allPartners.get(i).lastName,
								countries.get(current).allPartners.get(i).email,
								countries.get(current).allPartners.get(i).country,
								countries.get(current).allPartners.get(i).dates, });
					}

					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource(flags.get(current))));
					partnerLabel.setText(current + " Partners");
					countryConference.setText(current + " Conference will be: ");
					date.setText(countries.get(current).bestDay);
					attendence.setText(countries.get(current).avaliable.size() + " out of the "
							+ countries.get(current).allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}

			}

		});

		Container c = getContentPane();

		c.add(button);
		c.add(button2);
		c.add(radio);
		c.add(selectLabel);
		c.add(partnerLabel);
		c.add(js);
		c.add(flag);
		c.add(attendence);
		c.add(attendence2);
		c.add(date);
		c.add(countryConference);
	}

	public static void addPartners(Person p) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { p.firstName, p.lastName, p.email, p.country, p.dates });
		if (startPoint == 0) {
			model.removeRow(0);
			startPoint = 1;
		}

	}

	public static void transferPartners(Map mp) {
		countries = mp;
	}
}