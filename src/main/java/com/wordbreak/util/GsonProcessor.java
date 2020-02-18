package com.wordbreak.util;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步带缓存的线程池
 *
 */
public class GsonProcessor {

	private GsonProcessor() {
	}

	private static class AsynProcessorHolder {
		private final static Gson INSTANCE = new Gson();
	}

	public static Gson getInstance() {

		return AsynProcessorHolder.INSTANCE;
	}
}
