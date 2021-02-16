package com.ianlau.paloupload.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NRICGenerator {
	final static Random rand = new Random();
	
	public static String generateNRIC(String prefix, String year) {
		String series = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99998 + 1));
		String range = getRange(prefix, year, series);
		
		return prefix + range + series;
	}
	
	public static String getRange(String prefix, String year, String series) {
		String yearSegment = year.substring(2,4);
	      if (Integer.valueOf(year) >= 1968) {
	        return yearSegment;
	      }
	      
	      switch (prefix) {
	        case "S":
	          return Integer.valueOf(series) < 50000 ? "00" : "01"; 
	        case "F":
	          return Integer.valueOf(series) < 50000 ? "02" : "03";
	        default:
	          return yearSegment;
	      }
	}
	
	public static String generateRandomUin() {
		
		int randomPrefix = rand.nextInt(4);
		
		String[] prefixes =  {"S", "F", "G", "T"};
		
		String prefix = prefixes[randomPrefix];
		
		int randomYear;
		
		if(randomPrefix < 2) { // S or F
			randomYear = rand.nextInt(1000) + 1000; // Min = 1000, max = 1999
		}
		else {
			randomYear = rand.nextInt(3001) + 2000; // Min = 2000, max = 5000
		}

		String uin = generateNRIC(prefix, String.valueOf(randomYear));
		
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final int N = alphabet.length();


		return uin + alphabet.charAt(rand.nextInt(N));
	}
	
	 
}
