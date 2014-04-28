package com.nlp.trial.utils;

import java.util.HashMap;
import java.util.List;

public class MathUtils {
	public static HashMap<String, Integer> tf(List<String> list) {
		HashMap<String, Integer> map = new HashMap<>();
		for (String word : list) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}
		return map;
	}
}
