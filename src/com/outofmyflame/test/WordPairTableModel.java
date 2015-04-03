package com.outofmyflame.test;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wordList")
public class WordPairTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private ArrayList<String[]> test = new ArrayList<String[]>();

	@XmlElement(name="wordPair")
	private ArrayList<WordPair> wordList = new ArrayList<WordPair>();

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		try {
			// return test.size();
			return wordList.size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int row, int col) {

		if (col == 0) {
			return this.wordList.get(row).getNativeWord();
		} else {
			if (col == 1) {
				return this.wordList.get(row).getForeignWord();
			} else {
				return null;
			}
		}

		// return (this.test.get(row))[col];
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Muttersprache";
		case 1:
			return "Fremdsprache";
		default:
			return "xy";
		}
	}

	public void addRow(String nativeWord, String foreignWord) {
		// String[] row = { left, right };
		// this.test.add(row);
		this.wordList.add(new WordPair(nativeWord, foreignWord));
		this.fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeRow(int index) {
		this.wordList.remove(index);
		this.fireTableChanged(new TableModelEvent(this));
	}
	
	public ArrayList<WordPair> getWordList() {
		return wordList;
	}

}
