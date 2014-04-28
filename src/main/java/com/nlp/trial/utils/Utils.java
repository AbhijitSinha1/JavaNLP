package com.nlp.trial.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

public class Utils {
	private static CharArraySet stop_word_set;

	public static CharArraySet loadStopWords(File file) throws IOException {
		Collection<String> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String text;
		while ((text = br.readLine()) != null) {
			String[] words = text.split(",");
			for (String word : words) {
				list.add(word);
			}
		}
		br.close();
		stop_word_set = new CharArraySet(Version.LUCENE_47, list, true);
		return stop_word_set;
	}

	public static String tokenizeStopStem(String input) {

		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_47, new StringReader(input));
		tokenStream = new StopFilter(Version.LUCENE_47, tokenStream, stop_word_set);
		tokenStream = new PorterStemFilter(tokenStream);

		StringBuilder sb = new StringBuilder();
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttr = tokenStream.getAttribute(CharTermAttribute.class);
		try {
			while (tokenStream.incrementToken()) {
				if (sb.length() > 0) {
					sb.append(" ");
				}
				sb.append(charTermAttr.toString());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
}
