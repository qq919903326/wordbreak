package com.wordbreak.util;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GsonProcessor {

	private GsonProcessor() { }

	private static class AsynProcessorHolder {
		private final static Gson INSTANCE = new Gson();
	}

	public static Gson getInstance() {
		return AsynProcessorHolder.INSTANCE;
	}
}
