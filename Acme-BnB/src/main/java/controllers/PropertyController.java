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

import java.util.ArrayList;
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

import services.LessorService;
import services.PropertyService;
import services.RelatedValueService;
import domain.ExtraAttribute;
import domain.Lessor;
import domain.Property;
import domain.RelatedValue;
import form.PropertyForm;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController {

	//Supported services

	@Autowired
	private PropertyService		propertyService;

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private RelatedValueService	relatedValueService;


	//Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Property> properties = propertyService.findAll();

		result = new ModelAndView("property/list");
		result.addObject("properties", properties);
		result.addObject("RequestURI", "property/list.do");

		return result;
	}

	@RequestMapping(value = "/moreInfo", method = RequestMethod.GET)
	public ModelAndView moreInfo(@RequestParam int propertyId) {
		ModelAndView result;
		Collection<RelatedValue> relatedValues;
		Property property = propertyService.findOne(propertyId);
		relatedValues = property.getRelatedValues();

		result = new ModelAndView("property/moreInfo");
		result.addObject("relatedValues", relatedValues);
		result.addObject("RequestURI", "property/moreInfo.do");

		return result;
	}

	@RequestMapping(value = "/ownProperties", method = RequestMethod.GET)
	public ModelAndView lessorOwnProperties(@RequestParam(required = false) String editErrorMessage, @RequestParam(required = false) String deleteErrorMessage) {
		ModelAndView result;
		Lessor lessor = lessorService.findByPrincipal();
		Collection<Property> properties = lessor.getProperties();

		result = new ModelAndView("property/ownProperties");
		result.addObject("properties", properties);
		result.addObject("RequestURI", "property/ownProperties.do");
		result.addObject("editErrorMessage", editErrorMessage);
		result.addObject("deleteErrorMessage", deleteErrorMessage);
		result.addObject("lessorId", lessor.getId());

		return result;
	}

	//Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		PropertyForm propertyForm = propertyService.create();

		result = createEditModelAndView(propertyForm);

		return result;
	}

	//Save

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid PropertyForm propertyForm, BindingResult binding) {
		ModelAndView result;

		Property property = propertyService.reconstruct(propertyForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(propertyForm);
		} else {
			try {
				propertyService.save(property);
				result = new ModelAndView("redirect:/property/ownProperties.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(propertyForm, "property.save.error");
			}
		}
		return result;
	}

	//Edit

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId) {
		ModelAndView result;

		try {
			Property property = propertyService.findOne(propertyId);
			Lessor lessor = lessorService.findByPrincipal();
			Assert.isTrue(property.getLessor().equals(lessor));

			PropertyForm propertyForm = propertyService.conversionToFormObject(property);

			result = createEditModelAndView(propertyForm);
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/property/ownProperties.do");
			result.addObject("editErrorMessage", "property.edit.error");
		}

		return result;
	}

	//Delete

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int propertyId) {
		ModelAndView result;

		try {
			Property property = propertyService.findOne(propertyId);

			propertyService.delete(property);
			result = new ModelAndView("redirect:/property/ownProperties.do");
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/property/ownProperties.do");
			result.addObject("deleteErrorMessage", "property.delete.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(PropertyForm propertyForm) {
		return createEditModelAndView(propertyForm, null);
	}

	protected ModelAndView createEditModelAndView(PropertyForm propertyForm, String message) {
		ModelAndView result;

		if (propertyForm.getRelatedValues() == null) {
			Collection<ExtraAttribute> extraAttributes = propertyService.getDefaultExtraAttributes();
			Collection<RelatedValue> relatedValues = new ArrayList<>();

			for (ExtraAttribute extraAttribute : extraAttributes) {
				RelatedValue relatedValue = relatedValueService.create();
				relatedValue.setExtraAttribute(extraAttribute);
				relatedValues.add(relatedValue);
			}
			propertyForm.setExtraAttributes(extraAttributes);
			propertyForm.setRelatedValues(relatedValues);
		}

		result = new ModelAndView("property/create");
		result.addObject("propertyForm", propertyForm);
		result.addObject("errorMessage", message);
		result.addObject("RequestURI", "property/save.do");

		return result;
	}
}
