package com.ianlau.paloupload.upload;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ianlau.paloupload.upload.model.TbPaloOrder;
import com.ianlau.paloupload.upload.service.UploadService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UploadTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	UploadService uploadService;
	
	private final String headers = "Region,Country,Item Type,Sales Channel,Order Priority,Order Date,Order ID,Ship Date,Units Sold,Unit Price,Unit Cost,Total Revenue,Total Cost,Total Profit";
	private final String sampleData = "Sub-Saharan Africa,South Africa,Fruits,Offline,M,7/27/2012,443368995,7/28/2012,1593,9.33,6.92,14862.69,11023.56,3839.13";
	private final String sampleInvalid = "Middle East and North Africa,Morocco,Clothes,Online,M,9/14/2013,667593514,10/19/2013,4611,109.28,35.84,503890.08,,338631.84";
	
	@Test
	public void testUploadValid() 
	  throws Exception {
		
		String data = headers+"\n"+sampleData;
		
	    MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "hello.csv", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        data.getBytes()
	      );

	    MockMvc mockMvc 
	      = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    mockMvc.perform(multipart("/upload").file(file))
	      .andExpect(status().isOk());
	    
	    List<TbPaloOrder> result = uploadService.getAll();
	    
	    assertThat(result).hasSize(1);
	}
	
	@Test
	public void testUploadWithInvalidData() 
	  throws Exception {
		
		String data = headers+"\n"+sampleInvalid;
		
	    MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "hello.csv", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        data.getBytes()
	      );

	    MockMvc mockMvc 
	      = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    mockMvc.perform(multipart("/upload").file(file))
	      .andExpect(status().isOk());
	    
	    List<TbPaloOrder> result = uploadService.getAll();
	    
	    assertThat(result).hasSize(0);
	    
	    data = headers+"\n"+sampleInvalid+"\n"+sampleData+"\n"+sampleData;
	    
	    file 
	      = new MockMultipartFile(
	        "file", 
	        "hello.csv", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        data.getBytes()
	      );
	    
	    mockMvc.perform(multipart("/upload").file(file))
	      .andExpect(status().isOk());
	    
	    result = uploadService.getAll();
	    
	    assertThat(result).hasSize(2);
	}
}
