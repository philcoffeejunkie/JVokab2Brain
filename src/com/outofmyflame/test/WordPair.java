package com.outofmyflame.test;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WordPair {
	private String foreignWord;
	private String nativeWord;

	public WordPair() {
		
	}
	
	public WordPair(String nativeWord, String foreignWord) {
		super();
		this.foreignWord = foreignWord;
		this.nativeWord = nativeWord;
	}

	public String getForeignWord() {
		return foreignWord;
	}

	public void setForeignWord(String foreignWord) {
		this.foreignWord = foreignWord;
	}

	public String getNativeWord() {
		return nativeWord;
	}

	public void setNativeWord(String nativeWord) {
		this.nativeWord = nativeWord;
	}
}
