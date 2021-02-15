package com.ianlau.paloupload.util;

import java.util.concurrent.ThreadLocalRandom;

public class NRICGenerator {
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
	
	 
}
