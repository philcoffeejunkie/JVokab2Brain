package com.outofmyflame.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import java.awt.Window.Type;

//public class QuizFrame extends JFrame {
public class QuizFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3119564323675924628L;

	private JPanel contentPane;
	private JTextField textFieldNativeWord;
	private JTextField textFieldForeignWord;
	// private static Timer timeControl;
	private Timer timeControl;
	private JLabel lblVerbleibendeZeit;
	private long startTime;
	private long endTime;

//	private ArrayList<WordPair> wordList;
	private JButton btnOk;

	private int answerTime = 30;
	private int currentWord;
	//private JFrame main;

	/**
	 * Create the frame.
	 */
	public QuizFrame(final ArrayList<WordPair> wordList, final JFrame main) {
		setModal(true);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				resetAndStartTimer();
				// System.out.println("form shown");
			}

		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				main.setVisible(true);
			}
		});
		
		
		setBounds(100, 100, 364, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNativeWord = new JTextField();
		textFieldNativeWord.setEnabled(false);
		textFieldNativeWord.setBounds(69, 68, 197, 20);
		contentPane.add(textFieldNativeWord);
		textFieldNativeWord.setColumns(10);

		textFieldForeignWord = new JTextField();
		textFieldForeignWord.setBounds(69, 128, 197, 20);
		contentPane.add(textFieldForeignWord);
		textFieldForeignWord.setColumns(10);

		JLabel lblFremdsprache = new JLabel("Muttersprache:");
		lblFremdsprache.setBounds(69, 49, 105, 14);
		contentPane.add(lblFremdsprache);

		JLabel lblFremdsprache_1 = new JLabel("Fremdsprache:");
		lblFremdsprache_1.setBounds(69, 108, 90, 14);
		contentPane.add(lblFremdsprache_1);

		lblVerbleibendeZeit = new JLabel("Verbleibende Zeit: 30 Sekunden");
		lblVerbleibendeZeit.setBounds(69, 153, 197, 14);
		contentPane.add(lblVerbleibendeZeit);

		timeControl = new Timer(100, null);

		timeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("timer-action");
				Date d = new Date();
				long remain;
				int secs;
				remain = endTime - d.getTime();
				secs = (int) (remain / 1000);
				if (secs >= 0) {
					lblVerbleibendeZeit.setText("Verbleibende Zeit: " + secs
							+ " Sekunden");
				} else {
					// Zeit rum
					lblVerbleibendeZeit.setText("Zeit abgelaufen!!");
					// timer stoppen
					// prüfen falsch/richtig
					// nächstes Wort
				}
			}
		});

//		this.wordList = wordList;

		currentWord = 0;
		textFieldNativeWord.setText(wordList.get(currentWord).getNativeWord());

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// timer stoppen
				timeControl.stop();

				// prüfen falsch/richtig
				String foreignWord = wordList.get(currentWord).getForeignWord();
				if (foreignWord.equals(textFieldForeignWord.getText())) {
					// richtig
					JOptionPane.showMessageDialog(null, "Richtig!", "Richtig",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// falsch
					JOptionPane.showMessageDialog(null, "Falsch! Es heißt: "
							+ foreignWord, "Falsch",
							JOptionPane.INFORMATION_MESSAGE);
				}

				textFieldForeignWord.setText("");

				// nächstes Wort
				currentWord++;
				textFieldNativeWord.setText(wordList.get(currentWord)
						.getNativeWord());
				// reset Timer
				resetAndStartTimer();
				textFieldForeignWord.requestFocus();
			}
		});
		btnOk.setBounds(177, 178, 89, 23);
		contentPane.add(btnOk);
	}

	private void resetAndStartTimer() {
		Date d = new Date();
		startTime = d.getTime();
		// endTime = startTime + 30 * 1000;
		endTime = startTime + answerTime * 1000;
		timeControl.start();
	}
}
