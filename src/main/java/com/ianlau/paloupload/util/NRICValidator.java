package com.ianlau.paloupload.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NRICValidator {
	private static final Logger log = LoggerFactory.getLogger(NRICValidator.class);

	public static boolean chkNRIC(String uin) {
		if (StringUtils.isNotBlank(uin)) {
			uin = uin.toUpperCase();
			char second = uin.charAt(0);

			if (uin.length() != 9)
				return false;
			else if (second == 'S' || second == 's') {
				if (checkNRIC(uin) && checkMiddleValues(uin))
					return true;
			} else if (second == 'T' || second == 't') {
				if (checkNRIC(uin) && checkMiddleValues(uin))
					return true;
			} else if (second == 'F' || second == 'f') {
				if (checkFIN(uin) && checkMiddleValues(uin))
					return true;
			} else if (second == 'G' || second == 'g') {
				if (checkFIN(uin) && checkMiddleValues(uin))
					return true;
			}
			return false;
		} else {
			return false;
		}

	}

	public static boolean checkMiddleValues(String uin) {
		boolean flag = false;
		String temp = uin.toUpperCase();
		char nric[] = temp.toCharArray();
		for (int i = 1; i < 8; i++) {
			char c = nric[i];
			if (Character.isDigit(c)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		if (flag == true) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkNRIC(String uin) {
		int weight[] = { 2, 7, 6, 5, 4, 3, 2 };
		char chkDigit[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'Z', 'J' };
		String tmp = uin.toUpperCase();
		char uinArray[] = tmp.toCharArray();
		int sum = 0;
		for (int i = 1; i < 8; i++) {
			int t = uinArray[i];
			t -= 48;
			t *= weight[i - 1];
			sum += t;
		}
		if (uinArray[0] == 'T')
			sum += 4;
		int r1 = sum % 11;
		int chkBitPos = 11 - r1;
		if (chkDigit[chkBitPos - 1] == uinArray[8])

			return true;
		return false;
	}

	public static boolean checkFIN(String uin) {
		int weight[] = { 2, 7, 6, 5, 4, 3, 2 };
		char chkDigit[] = { 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X' };
		String tmp = uin.toUpperCase();
		char uinArray[] = tmp.toCharArray();
		int sum = 0;
		for (int i = 1; i < 8; i++) {
			int t = uinArray[i];
			t -= 48;
			t *= weight[i - 1];
			sum += t;
		}
		if (uinArray[0] == 'G')
			sum += 4;
		int r1 = sum % 11;
		int chkBitPos = 11 - r1;
		if (chkDigit[chkBitPos - 1] == uinArray[8])
			return true;
		return false;
	}
}
