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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.LessorService;
import domain.Actor;
import domain.Lessor;
import domain.SocialIdentity;
import form.ActorForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	//Supported services

	@Autowired
	private ActorService	actorService;

	@Autowired
	private LessorService	lessorService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) String correctMessage, @RequestParam(required = false) String editErrorMessage, @RequestParam(required = false) String deleteErrorMessage) {
		ModelAndView result;

		Actor actor = actorService.findByPrincipal();

		ActorForm actorForm = new ActorForm();

		actorForm.setEmail(actor.getEmail());
		actorForm.setPhone(actor.getPhone());
		actorForm.setPicture(actor.getPicture());

		if (actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("LESSOR")) {
			Lessor lessor = lessorService.findByPrincipal();
			actorForm.setCreditCard(lessor.getCreditCard());
		}
		result = createEditModelAndView(actorForm);
		result.addObject("correctMessage", correctMessage);
		result.addObject("editErrorMessage", editErrorMessage);
		result.addObject("deleteErrorMessage", deleteErrorMessage);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActorForm actorForm, BindingResult binding) {
		ModelAndView result = new ModelAndView();
		Actor actor = actorService.findByPrincipal();
		Lessor lessor = null;

		if (actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("LESSOR")) {
			lessor = actorService.reconstructLessor(actorForm, binding);
		} else {
			actor = actorService.reconstruct(actorForm, binding);
		}

		if (binding.hasErrors()) {
			result = createEditModelAndView(actorForm);

		} else {
			try {
				if (actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("LESSOR")) {
					lessorService.save(lessor);
				} else {
					actorService.save(actor);
				}

				result = new ModelAndView("redirect:/actor/edit.do");
				result.addObject("correctMessage", "actor.edit.correct");
			} catch (Throwable oops) {
				if (actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("LESSOR")) {
					result = createEditModelAndView(actorForm, "lessor.commit.error");
				} else {
					result = createEditModelAndView(actorForm, "actor.commit.error");
				}
			}
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(ActorForm actorForm) {
		ModelAndView result = createEditModelAndView(actorForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(ActorForm actorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);

		Actor actor = actorService.findByPrincipal();
		Collection<SocialIdentity> socialIdentities = actor.getSocialIdentities();
		result.addObject("socialIdentities", socialIdentities);

		result.addObject("RequestURI", "actor/save.do");
		result.addObject("SocialIdentitiesRequestURI", "actor/edit.do");

		return result;
	}

}
