package com.ianlau.paloupload.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ianlau.paloupload.PalouploadApplication;
import com.ianlau.paloupload.upload.model.TbPaloOrder;

@SpringBootTest(classes = PalouploadApplication.class)
public class NRICTest {
	@Test
	public void testNricValidator() {

		assertThat(NRICValidator.chkNRIC("S1234567D")).isTrue();
		assertThat(NRICValidator.chkNRIC("S1234567A")).isFalse();
		
		assertThat(NRICValidator.chkNRIC("T3784091I")).isTrue();
		assertThat(NRICValidator.chkNRIC("T3784091A")).isFalse();
		
		assertThat(NRICValidator.chkNRIC("F3418905Q")).isTrue();
		assertThat(NRICValidator.chkNRIC("F3418905Z")).isFalse();
		
		assertThat(NRICValidator.chkNRIC("G4553558X")).isTrue();
		assertThat(NRICValidator.chkNRIC("G4553558Y")).isFalse();
		
		
		
		
	}	
	
	@Test
	public void testNricGenerator() {
		String randomUin;
		
		final String prefixes = "STFG";
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		// Test 500, 000 records
		for(int i = 0; i < 500000; i++) {
			randomUin = NRICGenerator.generateRandomUin();
			assertThat(randomUin.length()).isEqualTo(9);
			assertThat(prefixes.indexOf(randomUin.charAt(0))).isGreaterThan(-1);
			
			assertThat(alphabet.indexOf(randomUin.charAt(randomUin.length()-1))).isGreaterThan(-1);
		}

	}	
	
	@Test
	public void testNricGeneratorAndValidator() {
		String randomUin;
		
		int passCount = 0;
		
		// Test 500, 000 records
		for(int i = 0; i < 500000; i++) {
			randomUin = NRICGenerator.generateRandomUin();
			if(NRICValidator.chkNRIC(randomUin)) {
				passCount++;
			}
		}
		
		System.out.println(passCount);
		
		assertThat(passCount).isGreaterThan(10000);

	}	
}
