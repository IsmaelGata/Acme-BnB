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

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import domain.Lessor;
import form.LessorForm;

@Controller
@RequestMapping("/lessor")
public class LessorController extends AbstractController {

	@Autowired
	private LessorService	lessorService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam int lessorId) {
		ModelAndView result;
		Lessor lessor = lessorService.findOne(lessorId);

		result = new ModelAndView("lessor/show");
		result.addObject("lessor", lessor);
		result.addObject("RequestURI", "lessor/show.do");

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		LessorForm lessorForm = new LessorForm();
		result = createEditModelAndView(lessorForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LessorForm lessorForm, BindingResult binding) {
		ModelAndView result = new ModelAndView();
		Lessor lessor;

		lessor = lessorService.reconstruct(lessorForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(lessorForm, "lessor.creditCardPosibleBinding");

		} else {
			try {
				if (lessorService.checkCreditCard(lessorForm.getCreditCard())) {
					Boolean var = LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(lessorForm.getCreditCard().getNumber());
					if (var == false) {
						throw new IllegalArgumentException("invalid credit card number");
					}

					if (lessorForm.getCreditCard().getNumber().length() < 12) {
						throw new IllegalArgumentException("invalid credit card number");
					}
				}
				lessorService.save(lessor);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(lessorForm, "lessor.commit.error");
			}
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(LessorForm lessorForm) {
		ModelAndView result = createEditModelAndView(lessorForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(LessorForm lessorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessorForm);
		result.addObject("message", message);

		return result;
	}

}
