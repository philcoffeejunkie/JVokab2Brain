package com.outofmyflame.test;

//import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldLeft;
	private JTextField textFieldRight;
	private QuizFrame qFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
							.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("JVokab2Brain");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		WordPairTableModel tm = new WordPairTableModel();

		textFieldLeft = new JTextField();
		textFieldLeft.setBounds(29, 351, 207, 23);
		contentPane.add(textFieldLeft);
		textFieldLeft.setColumns(10);

		textFieldRight = new JTextField();
		textFieldRight.setBounds(246, 351, 202, 23);
		contentPane.add(textFieldRight);
		textFieldRight.setColumns(10);

		JButton addWordButton = new JButton("Hinzuf\u00FCgen");
		addWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				WordPairTableModel tm = (WordPairTableModel) table.getModel();

				// tm.addData(textFieldLeft.getText());
				tm.addRow(textFieldLeft.getText(), textFieldRight.getText());

				textFieldLeft.setText("");
				textFieldRight.setText("");
				textFieldLeft.requestFocus();
			}
		});
		addWordButton.setBounds(326, 385, 122, 23);
		contentPane.add(addWordButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 24, 419, 316);
		contentPane.add(scrollPane);
		table = new JTable(tm);

		scrollPane.setViewportView(table);

		JButton startQuizButton = new JButton("Abfrage");
		startQuizButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startQuiz();
			}

		});

		startQuizButton.setBounds(473, 24, 166, 33);
		contentPane.add(startQuizButton);

		// Tabelle mit einigen Demodaten füllen....
		JButton demodatenButton = new JButton("Demodaten");
		demodatenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WordPairTableModel tm = (WordPairTableModel) table.getModel();

				tm.addRow("die Vorstellung", "la presentación");
				tm.addRow("die", "la");
				tm.addRow("die Straße", "la calle");
				tm.addRow("in", "en");
				tm.addRow("Hauptstadt von Spanien", "Madrid");
				tm.addRow("Entschuldigung", "perdona");
				tm.addRow("wo", "dónde");
				tm.addRow("sein, befinden", "estar");
				tm.addRow("der Platz", "la plaza");
				tm.addRow("an dem", "al");
				tm.addRow("das Ende", "el final");
				tm.addRow("von", "de");
				tm.addRow("sein", "ser");
				tm.addRow("wie", "cómo");
				tm.addRow("dich", "te");
				tm.addRow("heißen, sich nennen", "llamar(se)");
				tm.addRow("mich", "me");
				tm.addRow("sein", "ser");
				tm.addRow("spanisch", "español");
				tm.addRow("nein, nicht", "no");
				tm.addRow("kolumbianisch", "colombiano");
				tm.addRow("aber", "pero");
				tm.addRow("mein(e)", "mi");
				tm.addRow("die Mutter", "la madre");
				tm.addRow("wer", "quién");
				tm.addRow("sein", "ser");

			}
		});
		demodatenButton.setBounds(29, 385, 89, 23);
		contentPane.add(demodatenButton);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				// Filter
				int state = fileChooser.showSaveDialog(null);

				if (state == JFileChooser.APPROVE_OPTION) {

					try {
						// braucht Zeit und speicher, außerhalb später
						JAXBContext context = JAXBContext
								.newInstance(WordPairTableModel.class);
						Marshaller m = context.createMarshaller();
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
								Boolean.TRUE);
						m.marshal((WordPairTableModel) table.getModel(),
								fileChooser.getSelectedFile());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnSpeichern.setBounds(473, 85, 166, 23);
		contentPane.add(btnSpeichern);

		JButton btnLaden = new JButton("Laden");
		btnLaden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// Filter
				int state = fileChooser.showOpenDialog(null);

				if (state == JFileChooser.APPROVE_OPTION) {
					WordPairTableModel model;
					try {
						// braucht Zeit und speicher, außerhalb später
						JAXBContext context = JAXBContext
								.newInstance(WordPairTableModel.class);
						Unmarshaller um = context.createUnmarshaller();

						model = (WordPairTableModel) um.unmarshal(fileChooser.getSelectedFile());
						table.setModel(model);

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnLaden.setBounds(473, 119, 166, 23);
		contentPane.add(btnLaden);
		
		JButton btnNewButton = new JButton("Löschen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Abfrage?
				
				WordPairTableModel tm = (WordPairTableModel) table.getModel();
				int[] selected = table.getSelectedRows();
				
				for (int i=0; i < selected.length; i++) {
					tm.removeRow(selected[0]);
				}
			}
		});
		btnNewButton.setBounds(473, 173, 166, 23);
		contentPane.add(btnNewButton);
	}

	protected void startQuiz() {
		qFrame = new QuizFrame(
				((WordPairTableModel) table.getModel()).getWordList(), this);
		this.setVisible(false);
		qFrame.setLocationRelativeTo(null);
		qFrame.setVisible(true);
	}
}
