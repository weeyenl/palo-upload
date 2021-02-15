package com.ianlau.paloupload.upload.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ianlau.paloupload.upload.model.TbPaloOrder;
import com.ianlau.paloupload.upload.repository.TbPaloOrderRepository;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class UploadService {

	@Autowired
	private TbPaloOrderRepository tbPaloOrderRepository;
	
	@Autowired
	private OrderProcessService orderProcessService;

	public TbPaloOrder save(TbPaloOrder order) {
		return tbPaloOrderRepository.save(order);
	}

	public TbPaloOrder getOrder(String id) {
		return tbPaloOrderRepository.findById(id).get();
	}
	
	public List<TbPaloOrder> getAll() {
		return tbPaloOrderRepository.findAll();
	}
	
	public String processOrder(MultipartFile file) throws CsvValidationException, FileNotFoundException, IOException {
		tbPaloOrderRepository.deleteAll();
		
		Map<String, Object> resultMap = orderProcessService.processUploadedFile(file);
		
		List<TbPaloOrder> resultList = (List<TbPaloOrder>)resultMap.get("resultList");
		Integer failedCount = (Integer) resultMap.get("failedCount");
		Integer totalCount = (Integer) resultMap.get("totalCount");
		
		tbPaloOrderRepository.saveAll(resultList);
		
		StringBuilder sb = new StringBuilder("");
		sb.append("Total number of records: " + totalCount + "<br>");
		sb.append("Number of records processed: " + resultList.size() + "<br>");
		sb.append("Number of records failed: " + failedCount + "<br>");
		
		return sb.toString();
	}
}
