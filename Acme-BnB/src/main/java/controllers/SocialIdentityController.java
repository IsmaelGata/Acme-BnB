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
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;
import form.PropertyForm;
import form.SocialIdentityForm;
import services.ActorService;
import services.SocialIdentityService;

@Controller
@RequestMapping("/socialIdentity")
public class SocialIdentityController extends AbstractController {

	//Supported services

	@Autowired
	private SocialIdentityService		socialIdentityService;
	
	@Autowired
	private ActorService				actorService;

	//Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
        ModelAndView result;
        	Actor actor= actorService.findByPrincipal();
            Collection<SocialIdentity> socialIdentities = actor.getSocialIdentities();
            result = new ModelAndView("socialIdentity/list");
            result.addObject("socialIdentities", socialIdentities);	
            result.addObject("RequestURI", "socialIdentity/list.do");	
            return result;
	}


	//Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SocialIdentityForm socialIdentityForm = new SocialIdentityForm();

		result = createEditModelAndView(socialIdentityForm);

		return result;
	}

	//Save

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid SocialIdentityForm socialIdentityForm, BindingResult binding) {
		ModelAndView result;

		SocialIdentity socialIdentity = socialIdentityService.reconstruct(socialIdentityForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentityForm);
		} else {
			try {
				socialIdentityService.save(socialIdentity);
				result = new ModelAndView("redirect:/socialIdentity/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(socialIdentityForm, "socialIdentity.save.error");
			}
		}
		return result;
	}

	//Edit

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int socialIdentityId) {
		ModelAndView result;

		try {
			SocialIdentity socialIdentity = socialIdentityService.findOne(socialIdentityId);
			Actor actor = actorService.findByPrincipal();
			Assert.isTrue(socialIdentity.getActor().equals(actor));

			SocialIdentityForm socialIdentityForm = socialIdentityService.conversionToFormObject(socialIdentity);

			result = createEditModelAndView(socialIdentityForm);
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/socialIdentity/list.do");
			result.addObject("editErrorMessage", "socialIdentity.edit.error");
		}

		return result;
	}

	//Delete

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int socialIdentityId) {
		ModelAndView result;

		try {
			SocialIdentity socialIdentity = socialIdentityService.findOne(socialIdentityId);
			
			Actor actor = actorService.findByPrincipal();
			Assert.isTrue(socialIdentity.getActor().equals(actor));
			
			socialIdentityService.delete(socialIdentity);
			result = new ModelAndView("redirect:/socialIdentity/list.do");
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/socialIdentity/list.do");
			result.addObject("deleteErrorMessage", "socialIdentity.delete.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(SocialIdentityForm socialIdentityForm) {
		return createEditModelAndView(socialIdentityForm, null);
	}

	protected ModelAndView createEditModelAndView(SocialIdentityForm socialIdentityForm, String message) {
		ModelAndView result;

		result = new ModelAndView("socialIdentity/create");
		result.addObject("socialIdentityForm", socialIdentityForm);
		result.addObject("errorMessage", message);
		result.addObject("RequestURI", "socialIdentity/save.do");

		return result;
	}
}
