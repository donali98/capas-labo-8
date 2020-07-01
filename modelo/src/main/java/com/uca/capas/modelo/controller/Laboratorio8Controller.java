package com.uca.capas.modelo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Laboratorio8Controller {

	//MENU PRINCIPAL LABORATORIO 8
	@RequestMapping("/indexLaboratorio8")
	public ModelAndView indexLaboratorio8() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Laboratorio/indexLaboratorio8");
		return mav;
	}
}
