package com.nlp.trial;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.util.CharArraySet;
import org.junit.Assert;
import org.junit.Test;

import com.nlp.trial.utils.Utils;

public class LuceneTest {
	private static final String PATH = "src/main/resources/";

	@Test
	public void testFileAccess() {
		File file = new File(PATH + "stop_words");
		Assert.assertTrue(file.exists());
	}

	@Test
	public void testStopWordsLoaded() throws IOException {
		CharArraySet set = Utils.loadStopWords(new File(PATH + "stop_words"));
		Assert.assertNotNull(set);
	}

	@Test
	public void tokenStopStemTest() throws IOException {
		Utils.loadStopWords(new File(PATH + "stop_words"));
		System.out.println(Utils.tokenizeStopStem("i am a good boy"));
	}
}
