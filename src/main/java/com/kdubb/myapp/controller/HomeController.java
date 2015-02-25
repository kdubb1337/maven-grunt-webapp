package com.kdubb.myapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {
	
	private static final Logger LOG = LogManager.getLogger(HomeController.class);
	
	@RequestMapping("")
	public String home(Principal currentUser, Model model, HttpServletResponse response) {
		return "index";
	}
	
	@ResponseBody
	@ExceptionHandler(HttpSessionRequiredException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="The session has expired")
	public String handleSessionExpired() {
		LOG.info("sessionExpired!!");
		return "sessionExpired";
	}
}