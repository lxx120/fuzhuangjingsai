package com.match.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class skuMergeUtil {

	public static List<String> mergeSkudes(List<List<String>> list) {

		int iterateSize = 1;// 总迭代次数，即组合总种数
		for (int i = 0; i < list.size(); i++) {
			// 每个List的n选1选法种数
			// 有兴趣的话可以扩展n选2，n选3，... n选x
			iterateSize *= list.get(i).size();
		}

		int median = 1; // 当前元素与左边已定元素的组合种数
		Map<Integer, skuMergeFuZhu> indexMap = new HashMap<Integer, skuMergeFuZhu>();
		for (int i = 0; i < list.size(); i++) {
			median *= list.get(i).size();
			skuMergeFuZhu sign = new skuMergeFuZhu();
			sign.index = 0;
			sign.whenChg = iterateSize / median;
			indexMap.put(i, sign);
		}

		System.out.println("条目总数: " + iterateSize);
		List<String> sets = new ArrayList<String>();

		int i = 1; // 组合编号

		while (i <= iterateSize) {
			String s = "";

			// m值可变
			for (int m = 0; m < list.size(); m++) {
				int whenChg = indexMap.get(m).whenChg; // 组元素更换频率
				int index = indexMap.get(m).index; // 组元素索引位置

				s += list.get(m).get(index) + "-";

				if (i % whenChg == 0) {
					index++;
					// 该组中的元素组合完了，按照元素索引顺序重新取出再组合
					if (index >= list.get(m).size()) {
						index = 0;
					}

					indexMap.get(m).index = index;
				}
			}

			s=s.substring(0, s.length()-1);
			System.out.println(s);
			sets.add(s);
			i++;
		}

		return sets;
	}

	public static List<String> mergeSku(List<List<Long>> list) {

		int iterateSize = 1;// 总迭代次数，即组合总种数
		for (int i = 0; i < list.size(); i++) {
			// 每个List的n选1选法种数
			// 有兴趣的话可以扩展n选2，n选3，... n选x
			iterateSize *= list.get(i).size();
		}

		int median = 1; // 当前元素与左边已定元素的组合种数
		Map<Integer, skuMergeFuZhu> indexMap = new HashMap<Integer, skuMergeFuZhu>();
		for (int i = 0; i < list.size(); i++) {
			median *= list.get(i).size();
			skuMergeFuZhu sign = new skuMergeFuZhu();
			sign.index = 0;
			sign.whenChg = iterateSize / median;
			indexMap.put(i, sign);
		}

		System.out.println("条目总数: " + iterateSize);
		List<String> sets = new ArrayList<String>();

		int i = 1; // 组合编号

		while (i <= iterateSize) {
			String s = "";

			// m值可变
			for (int m = 0; m < list.size(); m++) {
				int whenChg = indexMap.get(m).whenChg; // 组元素更换频率
				int index = indexMap.get(m).index; // 组元素索引位置

				s += list.get(m).get(index) + "-";

				if (i % whenChg == 0) {
					index++;
					// 该组中的元素组合完了，按照元素索引顺序重新取出再组合
					if (index >= list.get(m).size()) {
						index = 0;
					}

					indexMap.get(m).index = index;
				}
			}

			s=s.substring(0, s.length()-1);
			System.out.println(s);
			sets.add(s);
			i++;
		}

		return sets;
	}
}
