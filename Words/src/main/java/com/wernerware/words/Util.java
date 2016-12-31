package com.wernerware.words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Util {

	public static ArrayList<String> getWords(String dictionaryFilePath)
			throws FileNotFoundException, IOException {
		File file = new File(dictionaryFilePath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> rawWords = new ArrayList<String>();
		while( br.ready() ){
			rawWords.add(br.readLine().trim());
		}
		
		br.close();
		return rawWords;
	}
	
}
