package com.bhz.lht.mystudy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestForEach {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("key-a", "value-a");
		map.put("key-b", "value-b");
		map.put("key-c", "value-c");
		map.put("key-d", "value-d");
		map.put("key-e", "value-e");
		map.put("key-f", "value-f");
		map.put("key-g", "value-g");
		map.put("key-h", "value-h");
		map.put("key-i", "value-i");
		map.put("key-j", "value-j");
		map.put("key-k", "value-k");
		map.put("key-l", "value-l");
		map.put("key-m", "value-m");
		map.put("key-n", "value-n");
		map.put("key-o", "value-o");
		map.put("key-p", "value-p");
		map.put("key-q", "value-q");
		map.put("key-r", "value-r");
		map.put("key-s", "value-s");
		map.put("key-t", "value-t");
		map.put("key-u", "value-u");
		map.put("key-v", "value-v");
		map.put("key-w", "value-w");
		map.put("key-x", "value-x");
		map.put("key-y", "value-y");
		map.put("key-z", "value-z");

		map.entrySet().parallelStream().forEachOrdered(e -> list.add(e.getValue()));

		list.forEach(System.out::println);
		System.out.println("total: " + list.size());

	}

}
