
package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExtraAttributeService;
import domain.ExtraAttribute;
import domain.Type;

@Controller
@RequestMapping("/extraAttribute")
public class ExtraAttributeController extends AbstractController {

	//Supported services

	@Autowired
	private ExtraAttributeService	extraAttributeService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) String editErrorMessage, @RequestParam(required = false) String deleteErrorMessage) {
		ModelAndView result;
		Collection<ExtraAttribute> extraAttributes = extraAttributeService.findAll();

		result = new ModelAndView("extraAttribute/list");
		result.addObject("extraAttributes", extraAttributes);
		result.addObject("RequestURI", "extraAttribute/list.do");
		result.addObject("editErrorMessage", editErrorMessage);
		result.addObject("deleteErrorMessage", deleteErrorMessage);

		return result;
	}

	// Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ExtraAttribute extraAttribute = new ExtraAttribute();

		result = createEditModelAndView(extraAttribute);

		return result;
	}

	//Save

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam ExtraAttribute extraAttribute, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(extraAttribute);
		} else {
			try {
				extraAttributeService.save(extraAttribute);
				result = new ModelAndView("redirect:/extraAttribute/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(extraAttribute, "extraAttribute.save.error");
			}
		}
		return result;
	}

	//Edit

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int extraAttributeId) {
		ModelAndView result;

		try {
			ExtraAttribute extraAttribute = extraAttributeService.findOne(extraAttributeId);
			Assert.notNull(extraAttribute);
			result = createEditModelAndView(extraAttribute);
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/extraAttribute/list.do");
			result.addObject("editErrorMessage", "extraAttribute.edit.error");
		}

		return result;
	}

	//Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int extraAttributeId) {
		ModelAndView result;

		try {
			ExtraAttribute extraAttribute = extraAttributeService.findOne(extraAttributeId);
			Assert.notNull(extraAttribute);
			extraAttributeService.delete(extraAttribute);

			result = new ModelAndView("redirect:/extraAttribute/list.do");
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/extraAttribute/list.do");
			result.addObject("deleteErrorMessage", "extraAttribute.delete.error");
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(ExtraAttribute extraAttribute) {
		return createEditModelAndView(extraAttribute, null);
	}

	protected ModelAndView createEditModelAndView(ExtraAttribute extraAttribute, String message) {
		ModelAndView result;

		result = new ModelAndView("extraAttribute/create");
		ArrayList<Type> enumConstants = new ArrayList<>(Arrays.asList(Type.values()));

		result.addObject("extraAttribute", extraAttribute);
		result.addObject("errorMessage", message);
		result.addObject("enumConstants", enumConstants);
		result.addObject("RequestURI", "extraAttribute/save.do");

		return result;
	}
}
