package com.wernerware.datacleanup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.wernerware.words.Util;
import com.wernerware.words.featurizers.MaxStringOnlyVowelsOrConsonants;

public class CleanDictionary {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String dictionaryFilePath = "c:\\files\\better-words.txt";
		ArrayList<String> rawWords = Util.getWords(dictionaryFilePath);
		
		File fileOut = new File("c:\\files\\better-word-filtered.txt");
		FileWriter fw = new FileWriter(fileOut);
		PrintWriter pw = new PrintWriter(fw);
		
		for( String str : rawWords ){
			if( str.length() != 2 || new MaxStringOnlyVowelsOrConsonants(1).featurize(str, null)[0] < .0001 ){
				pw.println(str);
			}
		}
		
		pw.close();
		
	}

}
