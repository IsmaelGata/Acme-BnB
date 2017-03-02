/*
 * CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Auditor;
import form.AuditorForm;
import services.AuditorService;

@Controller
@RequestMapping("/auditor")
public class AuditorController extends AbstractController {

	@Autowired
	private	AuditorService	auditorService;



	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		AuditorForm auditorForm = new AuditorForm();
		result = createEditModelAndView(auditorForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuditorForm auditorForm, BindingResult binding) {
		ModelAndView result = new ModelAndView();
		Auditor auditor;

		auditor=auditorService.reconstruct(auditorForm,binding);
		if(binding.hasErrors()){
			result= createEditModelAndView(auditorForm);
		}else{
			try{
				auditorService.save(auditor);
				result= new ModelAndView("redirect:/welcome/index.do");
			}catch(Throwable oops){
				result= createEditModelAndView(auditorForm,"auditor.commit.error");
			}
		}
		
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm) {
		ModelAndView result = createEditModelAndView(auditorForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("auditor/register");
		result.addObject("auditorForm", auditorForm);
		result.addObject("message", message);

		return result;
	}

}
