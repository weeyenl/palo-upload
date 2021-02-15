package com.ianlau.paloupload.upload.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ianlau.paloupload.upload.model.TbPaloOrder;
import com.ianlau.paloupload.upload.repository.TbPaloOrderRepository;

@Controller
@CrossOrigin("http://localhost:4200")
public class DataController {
	@Autowired
	TbPaloOrderRepository tbPaloOrderRepository;

	@GetMapping("/orders")
	public ResponseEntity<Map<String, Object>> getAllOrders(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort, @RequestParam(required = false) String order) {

		try {
			Pageable paging;
			
			if(sort != null && !"".equals(sort) && order != null) {
				if("desc".equalsIgnoreCase(order)) {
					paging = PageRequest.of(page-1, size, Sort.by(sort).descending());
				}
				else {
					paging = PageRequest.of(page-1, size, Sort.by(sort).ascending());
				}
				
			}
			else {
				paging = PageRequest.of(page, size);
			}
			

			Page<TbPaloOrder> data;
			
			data = tbPaloOrderRepository.findAll(paging);

			Map<String, Object> response = new HashMap<>();
			response.put("items", data.getContent());
			response.put("currentPage", data.getNumber());
			response.put("totalCount", data.getTotalElements());
			response.put("totalPages", data.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
