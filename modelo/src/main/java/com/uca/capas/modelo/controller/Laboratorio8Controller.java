package com.uca.capas.modelo.controller;

import com.uca.capas.modelo.domain.Cliente;
import com.uca.capas.modelo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class Laboratorio8Controller {

	@Autowired
	private ClienteService clienteService;

	private static final Logger logger = Logger.getLogger(Laboratorio8Controller.class.getName());
	//MENU PRINCIPAL LABORATORIO 8
	@RequestMapping("/indexLaboratorio8")
	public ModelAndView indexLaboratorio8() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Laboratorio/indexLaboratorio8");
		return mav;
	}

	//INSERTAR CLIENTE
	@RequestMapping("insertcliente")
	public ModelAndView nuevoCliente(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cliente",new Cliente());
		modelAndView.setViewName("Laboratorio/agregarCliente");
		return modelAndView;
	}
	//proc almacenado
	@RequestMapping("/procAlmacenadoJdbc")
	public ModelAndView procAlmacenadoJdbc() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("usuario", new Cliente());
		mav.setViewName("Laboratorio/procedimiento");

		return mav;
	}
	@RequestMapping("/ejecutarProcedimientoJdbc")
	public ModelAndView ejecutarProcedimiento(@RequestParam Integer cliente, @RequestParam Boolean estado) {
		ModelAndView mav = new ModelAndView();
		int resultado;
		resultado = clienteService.ejecutarProcJdbc(cliente, estado);
		mav.addObject("resultado", resultado);
		mav.setViewName("Laboratorio/resultado");

		return mav;
	}
	@RequestMapping(value="/savecliente", method= RequestMethod.POST)
	public ModelAndView saveCliente(@Valid @ModelAttribute("cliente") Cliente c, BindingResult r) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cliente", new Cliente());
		if(r.hasErrors()) {
			mav.addObject("resultado", 0);
			mav.setViewName("Laboratorio/agregarCliente");
		}
		else {
			Integer key = null;
			if(c.getCcliente() == null) {
				mav.addObject("resultado", 1);
				key = clienteService.insertClienteAutoId(c);
			}
			else {
				mav.addObject("resultado", 1);
				clienteService.updateCliente(c);
			}
			mav.addObject("resultado", key);
			mav.setViewName("Laboratorio/agregarCliente");
		}
		return mav;
	}
}
