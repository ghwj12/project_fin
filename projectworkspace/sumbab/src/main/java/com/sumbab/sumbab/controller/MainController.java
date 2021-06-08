package com.sumbab.sumbab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumbab.sumbab.model.category.SelectedCategory;


@Controller
public class MainController {
	
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String mainPageGET() {
		
			return "main";
			
		}
		
		@RequestMapping(value = "/sumbabStart", method = RequestMethod.GET)
		public String StartController(Model model) {
			model.addAttribute("selectedCategory", new SelectedCategory());
			return "sumbabStart";
		}
		
		@RequestMapping(value = "/aboutSumbab", method = RequestMethod.GET)
		public String aboutSumbab() {
			return "aboutSumbab";
		}
}
