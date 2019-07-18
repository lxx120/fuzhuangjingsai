package com.match.common;

/*
 * 组合记号辅助类 
 */
public class skuMergeFuZhu {

	/**
	 * 每组元素更换频率，即迭代多少次换下一个元素
	 */
	public int whenChg;
	/**
	 * 每组元素的元素索引位置
	 */
	public int index;

	public int getWhenChg() {
		return whenChg;
	}

	public void setWhenChg(int whenChg) {
		this.whenChg = whenChg;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
