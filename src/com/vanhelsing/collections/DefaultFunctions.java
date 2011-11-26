package com.vanhelsing.collections;

import java.util.HashMap;
import java.util.Map;

import com.vanhelsing.Classification;

public class DefaultFunctions {

	public static DefaultFunction<Integer> integerInitialization() {
		return new DefaultFunction<Integer>() {
	
			@Override
			public Integer initialize() {
				return Integer.valueOf(0);
			}
		};
	}

	public static DefaultFunction<Map<Classification, Integer>> defaultMapInitializer() {
		return new DefaultFunction<Map<Classification, Integer>>() {
	
			@Override
			public Map<Classification, Integer> initialize() {
				return new DefaultMap<Classification, Integer>(new HashMap<Classification, Integer>(), integerInitialization());
			}
		};
	}

}
