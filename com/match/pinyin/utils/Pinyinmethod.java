package com.match.pinyin.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author mengly
 * @version 创建时间：2015年9月1日 下午5:53:38 类说明
 */

public class Pinyinmethod {
	public static String pinyins(String src) {
		if (src == null)
			return null;
		char[] cs = null;
		cs = src.toCharArray();
		String[] t2 = new String[cs.length];
		HanyuPinyinOutputFormat hpof = new HanyuPinyinOutputFormat();
		hpof.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		hpof.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		hpof.setVCharType(HanyuPinyinVCharType.WITH_V);

		String t4 = "";
		int t0 = cs.length;
		try {
			for (int i = 0; i < t0; i++) {
				if (Character.toString(cs[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(cs[i], hpof);
					t4 += t2[0];
				} else {
					t4 += Character.toString(cs[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t4;
	}

	/**
	 * 拼音首字母
	 * 
	 * @param src
	 * @return
	 * @author mengly
	 * @version 创建时间：2015年9月1日 下午6:03:14
	 */
	public static String pinyinhds(String src) {
		String convert = "";
		for (int j = 0; j < src.length(); j++) {
			char word = src.charAt(j);
			// 提取汉字的首字母
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	public static String ToPinyin(String chinese) {
		String pinyinStr = "";
		char[] newChar = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < newChar.length; i++) {
			if (newChar[i] > 128) {
				try {
					pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinStr += newChar[i];
			}
		}
		return pinyinStr;
	}
}
