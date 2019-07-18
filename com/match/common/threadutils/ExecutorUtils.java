package com.match.common.threadutils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ExecutorUtils {
	private static ExecutorService  executor;
	private static ExecutorService  singleExecutor;
	public static ExecutorService getExecutor(){
		if(executor==null)
			executor=Executors.newCachedThreadPool();
		return executor;
	}
	public static ExecutorService getSingleExecutor(){
		if(singleExecutor==null)
			singleExecutor=Executors.newSingleThreadExecutor();
		return singleExecutor;
	}
}
