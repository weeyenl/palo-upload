package com.ianlau.paloupload.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	@RequestMapping(value = { "/web", "/" })
	public String forwardReq() {
		return "forward:/web/index.html";
	}
}
