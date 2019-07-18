package com.match.common.ParamFileter.test;

import java.nio.file.Paths;

import org.junit.Test;

import com.match.common.ParamFileter.Fileoperation;

public class Test111 {
	@Test
	public void test1(){
		Fileoperation.delFolder(Paths.get("F:\\tmp\\img"));
	}
}
