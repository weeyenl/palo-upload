package com.ianlau.paloupload.upload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.ianlau.paloupload.PalouploadApplication;
import com.ianlau.paloupload.upload.model.TbPaloOrder;
import com.ianlau.paloupload.upload.service.UploadService;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = PalouploadApplication.class)
public class DatabaseTest {
	@Autowired
	UploadService uploadService;
	
	@Test
	public void testDB() {

		TbPaloOrder newOrder = new TbPaloOrder();
		newOrder.setCountry("Singapore");
		
		TbPaloOrder savedOrder = uploadService.save(newOrder);
		
		TbPaloOrder retrievedOrder = uploadService.getOrder(savedOrder.getId());

		assertThat(retrievedOrder.getCountry()).isEqualTo("Singapore");
		
		
		
	}	
}
