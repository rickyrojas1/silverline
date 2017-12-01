package mess;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
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

		flags.put("Canada", "/res/canada.jpg");
		flags.put("United States", "/res/usflag.png");
		flags.put("United Kingdom", "/res/ukflag.jpg");
		flags.put("France", "/res/france.jpg");
		flags.put("Mexico", "/res/mexico.jpg");
		flags.put("Japan", "/res/japan.jpg");
		flags.put("Ireland", "/res/ireland.jpg");
		flags.put("Singapore", "/res/singapore.jpg");
		flags.put("Spain", "/res/spain.jpg");
		flags.put("All", "/res/silverlineflag.jpg");

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
=======
import java.util.ArrayList;

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
	static ArrayList<Person> allPartners;
	static Country francePartners;
	static Country usPartners;
	static Country ukPartners;
	static Country spainPartners;
	static Country irelandPartners;
	static Country japanPartners;
	static Country canadaPartners;
	static Country mexicoPartners;
	static Country singaporePartners;

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
		JComboBox<String> radio = new JComboBox<String>();

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

		flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/silverlineflag.jpg")));

		radio.addItem("All");
		radio.addItem("United States");
		radio.addItem("United Kingdom");
		radio.addItem("France");
		radio.addItem("Mexico");
		radio.addItem("Ireland");
		radio.addItem("Canada");
		radio.addItem("Japan");
		radio.addItem("Singapore");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (radio.getSelectedItem().equals("United States")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < usPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { usPartners.allPartners.get(i).firstName,
								usPartners.allPartners.get(i).lastName, usPartners.allPartners.get(i).email,
								usPartners.allPartners.get(i).country, usPartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("United Kingdom")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < ukPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { ukPartners.allPartners.get(i).firstName,
								ukPartners.allPartners.get(i).lastName, ukPartners.allPartners.get(i).email,
								ukPartners.allPartners.get(i).country, ukPartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Canada")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < canadaPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { canadaPartners.allPartners.get(i).firstName,
								canadaPartners.allPartners.get(i).lastName, canadaPartners.allPartners.get(i).email,
								canadaPartners.allPartners.get(i).country, canadaPartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("France")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < francePartners.allPartners.size(); i++) {
						model.addRow(new Object[] { francePartners.allPartners.get(i).firstName,
								francePartners.allPartners.get(i).lastName, francePartners.allPartners.get(i).email,
								francePartners.allPartners.get(i).country, francePartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Ireland")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < irelandPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { irelandPartners.allPartners.get(i).firstName,
								irelandPartners.allPartners.get(i).lastName, irelandPartners.allPartners.get(i).email,
								irelandPartners.allPartners.get(i).country, irelandPartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Japan")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < japanPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { japanPartners.allPartners.get(i).firstName,
								japanPartners.allPartners.get(i).lastName, japanPartners.allPartners.get(i).email,
								japanPartners.allPartners.get(i).country, japanPartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Mexico")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < mexicoPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { mexicoPartners.allPartners.get(i).firstName,
								mexicoPartners.allPartners.get(i).lastName, mexicoPartners.allPartners.get(i).email,
								mexicoPartners.allPartners.get(i).country, mexicoPartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Singapore")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < singaporePartners.allPartners.size(); i++) {
						model.addRow(new Object[] { singaporePartners.allPartners.get(i).firstName,
								singaporePartners.allPartners.get(i).lastName,
								singaporePartners.allPartners.get(i).email,
								singaporePartners.allPartners.get(i).country,
								singaporePartners.allPartners.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("All")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					countryConference.setText("     We currently have: ");

					for (int i = 0; i < allPartners.size(); i++) {
						model.addRow(new Object[] { allPartners.get(i).firstName, allPartners.get(i).lastName,
								allPartners.get(i).email, allPartners.get(i).country, allPartners.get(i).dates });
					}

				}
			}

		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (radio.getSelectedItem().equals("United States")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < usPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { usPartners.avaliable.get(i).firstName,
								usPartners.avaliable.get(i).lastName, usPartners.avaliable.get(i).email,
								usPartners.avaliable.get(i).country, usPartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("United Kingdom")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < ukPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { ukPartners.avaliable.get(i).firstName,
								ukPartners.avaliable.get(i).lastName, ukPartners.avaliable.get(i).email,
								ukPartners.avaliable.get(i).country, ukPartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Canada")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < canadaPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { canadaPartners.avaliable.get(i).firstName,
								canadaPartners.avaliable.get(i).lastName, canadaPartners.avaliable.get(i).email,
								canadaPartners.avaliable.get(i).country, canadaPartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("France")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < francePartners.avaliable.size(); i++) {
						model.addRow(new Object[] { francePartners.avaliable.get(i).firstName,
								francePartners.avaliable.get(i).lastName, francePartners.avaliable.get(i).email,
								francePartners.avaliable.get(i).country, francePartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Ireland")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < irelandPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { irelandPartners.avaliable.get(i).firstName,
								irelandPartners.avaliable.get(i).lastName, irelandPartners.avaliable.get(i).email,
								irelandPartners.avaliable.get(i).country, irelandPartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Japan")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < japanPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { japanPartners.avaliable.get(i).firstName,
								japanPartners.avaliable.get(i).lastName, japanPartners.avaliable.get(i).email,
								japanPartners.avaliable.get(i).country, japanPartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Mexico")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < mexicoPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { mexicoPartners.avaliable.get(i).firstName,
								mexicoPartners.avaliable.get(i).lastName, mexicoPartners.avaliable.get(i).email,
								mexicoPartners.avaliable.get(i).country, mexicoPartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("Singapore")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < singaporePartners.avaliable.size(); i++) {
						model.addRow(new Object[] { ukPartners.avaliable.get(i).firstName,
								singaporePartners.avaliable.get(i).lastName, singaporePartners.avaliable.get(i).email,
								singaporePartners.avaliable.get(i).country, singaporePartners.avaliable.get(i).dates });
					}

				}
				if (radio.getSelectedItem().equals("All")) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					int count = 0;
					countryConference.setText("     We currently have: ");

					for (int i = 0; i < usPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { usPartners.avaliable.get(i).firstName,
								usPartners.avaliable.get(i).lastName, usPartners.avaliable.get(i).email,
								usPartners.avaliable.get(i).country, usPartners.avaliable.get(i).dates });
						count++;
					}
					for (int i = 0; i < singaporePartners.avaliable.size(); i++) {
						model.addRow(new Object[] { ukPartners.avaliable.get(i).firstName,
								singaporePartners.avaliable.get(i).lastName, singaporePartners.avaliable.get(i).email,
								singaporePartners.avaliable.get(i).country, singaporePartners.avaliable.get(i).dates });
						count++;
					}
					for (int i = 0; i < mexicoPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { mexicoPartners.avaliable.get(i).firstName,
								mexicoPartners.avaliable.get(i).lastName, mexicoPartners.avaliable.get(i).email,
								mexicoPartners.avaliable.get(i).country, mexicoPartners.avaliable.get(i).dates });
						count++;
					}
					for (int i = 0; i < japanPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { japanPartners.avaliable.get(i).firstName,
								japanPartners.avaliable.get(i).lastName, japanPartners.avaliable.get(i).email,
								japanPartners.avaliable.get(i).country, japanPartners.avaliable.get(i).dates });
						count++;
					}
					for (int i = 0; i < irelandPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { irelandPartners.avaliable.get(i).firstName,
								irelandPartners.avaliable.get(i).lastName, irelandPartners.avaliable.get(i).email,
								irelandPartners.avaliable.get(i).country, irelandPartners.avaliable.get(i).dates });
						count++;
					}
					for (int i = 0; i < francePartners.avaliable.size(); i++) {
						model.addRow(new Object[] { francePartners.avaliable.get(i).firstName,
								francePartners.avaliable.get(i).lastName, francePartners.avaliable.get(i).email,
								francePartners.avaliable.get(i).country, francePartners.avaliable.get(i).dates });
						count++;
					}
					for (int i = 0; i < canadaPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { canadaPartners.avaliable.get(i).firstName,
								canadaPartners.avaliable.get(i).lastName, canadaPartners.avaliable.get(i).email,
								canadaPartners.avaliable.get(i).country, canadaPartners.avaliable.get(i).dates });
						count++;
					}

					for (int i = 0; i < ukPartners.avaliable.size(); i++) {
						model.addRow(new Object[] { ukPartners.avaliable.get(i).firstName,
								ukPartners.avaliable.get(i).lastName, ukPartners.avaliable.get(i).email,
								ukPartners.avaliable.get(i).country, ukPartners.avaliable.get(i).dates });
						count++;
					}
					attendence.setText(count + " out of the " + allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}
			}

		});

		radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox combo = (JComboBox) e.getSource();
				String current = (String) combo.getSelectedItem();

				if (current.equals("United States")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < usPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { usPartners.allPartners.get(i).firstName,
								usPartners.allPartners.get(i).lastName, usPartners.allPartners.get(i).email,
								usPartners.allPartners.get(i).country, usPartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/usflag.png")));
					partnerLabel.setText("U.S.A. Partners");
					countryConference.setText("U.S. Conference will be: ");
					date.setText(usPartners.bestDay);
					attendence.setText(usPartners.avaliable.size() + " out of the " + usPartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");
				}
				if (current.equals("United Kingdom")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < ukPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { ukPartners.allPartners.get(i).firstName,
								ukPartners.allPartners.get(i).lastName, ukPartners.allPartners.get(i).email,
								ukPartners.allPartners.get(i).country, ukPartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ukflag.jpg")));
					partnerLabel.setText("U.K. Partners");
					countryConference.setText("U.K. Conference will be: ");
					date.setText(ukPartners.bestDay);
					attendence.setText(ukPartners.avaliable.size() + " out of the " + ukPartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");
				}
				if (current.equals("Singapore")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < singaporePartners.allPartners.size(); i++) {
						model.addRow(new Object[] { singaporePartners.allPartners.get(i).firstName,
								singaporePartners.allPartners.get(i).lastName,
								singaporePartners.allPartners.get(i).email,
								singaporePartners.allPartners.get(i).country,
								singaporePartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/singapore.jpg")));
					partnerLabel.setText("Singapore Partners");
					countryConference.setText("Singapore Conference will be: ");
					date.setText(singaporePartners.bestDay);
					attendence.setText(
							singaporePartners.avaliable.size() + " out of the " + singaporePartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");
					partnerLabel.setBounds(770, 10, 400, 100);
				}
				if (current.equals("Japan")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < japanPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { japanPartners.allPartners.get(i).firstName,
								japanPartners.allPartners.get(i).lastName, japanPartners.allPartners.get(i).email,
								japanPartners.allPartners.get(i).country, japanPartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/japan.jpg")));
					partnerLabel.setText("Japan Partners");
					countryConference.setText("Japan Conference will be: ");
					date.setText(japanPartners.bestDay);
					attendence.setText(
							japanPartners.avaliable.size() + " out of the " + japanPartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}
				if (current.equals("Mexico")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < mexicoPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { mexicoPartners.allPartners.get(i).firstName,
								mexicoPartners.allPartners.get(i).lastName, mexicoPartners.allPartners.get(i).email,
								mexicoPartners.allPartners.get(i).country, mexicoPartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/mexico.jpg")));
					partnerLabel.setText("Mexico Partners");
					countryConference.setText("Mexico Conference will be: ");
					date.setText(mexicoPartners.bestDay);
					attendence.setText(
							mexicoPartners.avaliable.size() + " out of the " + mexicoPartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}
				if (current.equals("France")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < francePartners.allPartners.size(); i++) {
						model.addRow(new Object[] { francePartners.allPartners.get(i).firstName,
								francePartners.allPartners.get(i).lastName, francePartners.allPartners.get(i).email,
								francePartners.allPartners.get(i).country, francePartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/france.jpg")));
					partnerLabel.setText("France Partners");
					countryConference.setText("France Conference will be: ");
					date.setText(francePartners.bestDay);
					attendence.setText(
							francePartners.avaliable.size() + " out of the " + francePartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}
				if (current.equals("Canada")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < canadaPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { canadaPartners.allPartners.get(i).firstName,
								canadaPartners.allPartners.get(i).lastName, canadaPartners.allPartners.get(i).email,
								canadaPartners.allPartners.get(i).country, canadaPartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/canada.jpg")));
					partnerLabel.setText("Canada Partners");
					countryConference.setText("Canada Conference will be: ");
					date.setText(canadaPartners.bestDay);
					attendence.setText(
							canadaPartners.avaliable.size() + " out of the " + canadaPartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}
				if (current.equals("Ireland")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < irelandPartners.allPartners.size(); i++) {
						model.addRow(new Object[] { irelandPartners.allPartners.get(i).firstName,
								irelandPartners.allPartners.get(i).lastName, irelandPartners.allPartners.get(i).email,
								irelandPartners.allPartners.get(i).country, irelandPartners.allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ireland.jpg")));
					partnerLabel.setText("Ireland Partners");
					countryConference.setText("Ireland Conference will be: ");
					date.setText(irelandPartners.bestDay);
					attendence.setText(
							irelandPartners.avaliable.size() + " out of the " + irelandPartners.allPartners.size());
					attendence2.setText("Partners will be in attendence!");

				}

				if (current.equals("All")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < allPartners.size(); i++) {
						model.addRow(new Object[] { allPartners.get(i).firstName, allPartners.get(i).lastName,
								allPartners.get(i).email, allPartners.get(i).country, allPartners.get(i).dates });
					}
					flag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/silverlineflag.jpg")));
					partnerLabel.setText("All Partners");
					countryConference.setText("     We currently have: ");
					attendence.setText(" " + allPartners.size() + " Partners!");
					attendence2.setText("");
					date.setText("");
					partnerLabel.setBounds(800, 10, 400, 100);

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

	public static void transferPartners(Country france, Country us, Country uk, Country japan, Country mexico,
			Country singapore, Country canada, Country spain, Country ireland, ArrayList<Person> all) {

		francePartners = france;
		usPartners = us;
		ukPartners = uk;
		japanPartners = japan;
		mexicoPartners = mexico;
		singaporePartners = singapore;
		canadaPartners = canada;
		spainPartners = spain;
		irelandPartners = ireland;
		allPartners = all;
>>>>>>> refs/remotes/origin/master

	}

}
