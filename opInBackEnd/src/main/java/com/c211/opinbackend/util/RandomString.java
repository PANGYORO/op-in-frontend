package com.c211.opinbackend.util;

import java.util.Random;

public class RandomString {
	public static int generateNumber() {
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		return generator.nextInt(1000000) % 1000000;
	}
}
