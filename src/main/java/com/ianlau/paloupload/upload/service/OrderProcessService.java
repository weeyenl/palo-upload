package com.ianlau.paloupload.upload.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ianlau.paloupload.upload.model.TbPaloOrder;
import com.ianlau.paloupload.util.NRICGenerator;
import com.ianlau.paloupload.util.NRICValidator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class OrderProcessService {

	Logger logger = LoggerFactory.getLogger(OrderProcessService.class);
	
	public Map<String, Object> processUploadedFile(MultipartFile file) throws FileNotFoundException, IOException, CsvValidationException{
		Map<String, Object> resultMap = new HashMap<>();
		
		List<TbPaloOrder> orderList = new ArrayList<>();
		Integer totalCount = 0,  failCount = 0, failNricCount = 0;
		
		boolean skipLine = false;
		
		CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream())).withSkipLines(1).build();
				
        String[] lineInArray;
        while ((lineInArray = reader.readNext()) != null) {
    	  skipLine = false;
    	  totalCount++;
    	  
    	  if(lineInArray == null || lineInArray.length != 14) {
    		  failCount++;
    		  logger.info("Line has not enough/more than 14 fields.");
    		  continue;
    	  }
    	  
    	  
    	  for(String line : lineInArray) {
    		  if(line == null || "".equals(line)){
    			  failCount++;
    			  logger.info("Line has empty field.");
    			  skipLine = true;
    			  break;
    		  }
    	  }
    	  
    	  if(!skipLine) {
    		  TbPaloOrder newOrder = new TbPaloOrder(lineInArray);
    		  newOrder.setNric(NRICGenerator.generateRandomUin());
    		  
    		  if(NRICValidator.chkNRIC(newOrder.getNric())) {
    			  orderList.add(newOrder);
    		  }
    		  else {
    			  failNricCount++;
    		  }
    		  
    	  }
          
        }
	    
		reader.close();
		
		resultMap.put("resultList", orderList);
		resultMap.put("failedCount", failCount);
		resultMap.put("failNricCount", failNricCount);
		resultMap.put("totalCount", totalCount);
		return resultMap;
	}}
