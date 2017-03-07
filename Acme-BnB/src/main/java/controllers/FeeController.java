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

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Fee;
import form.FeeForm;
import services.FeeService;

@Controller
@RequestMapping("/fee")
public class FeeController extends AbstractController {

	//Supported services

	@Autowired
	private FeeService	feeService;



	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Collection<Fee> fee= feeService.findAll();
		FeeForm feeForm=new FeeForm();
		feeForm.setAmount(fee.iterator().next().getAmount());
		feeForm.setId(fee.iterator().next().getId());


		result = createEditModelAndView(feeForm);

		return result;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FeeForm feeForm, BindingResult binding) {
		ModelAndView result;
		Fee fee;

		fee = feeService.reconstruct(feeForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(feeForm);
		} else {
			try {
				feeService.save(fee);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(feeForm, "fee.commit.error");
			}
		}

		return result;
	}


	//Ancillary methods

	protected ModelAndView createEditModelAndView(FeeForm feeForm) {
		ModelAndView result = createEditModelAndView(feeForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(FeeForm feeForm, String message) {
		ModelAndView result;

		result = new ModelAndView("fee/edit");
		result.addObject("feeForm", feeForm);
		result.addObject("message", message);

		result.addObject("RequestURI", "fee/save.do");

		return result;
	}

}
