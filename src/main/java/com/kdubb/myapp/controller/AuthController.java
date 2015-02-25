package com.kdubb.myapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signin")
public class AuthController {

	private static final Logger LOG = LogManager.getLogger(AuthController.class);
	
	@RequestMapping("")
	public String signin(HttpServletRequest request, HttpServletResponse response, Model model, Principal currentUser) {
		LOG.info("Trying to sign in");
		response.addHeader("page", "signin");
		return "signin2";
	}
}
