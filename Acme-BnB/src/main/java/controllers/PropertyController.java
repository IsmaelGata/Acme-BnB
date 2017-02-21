/* CustomerController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Property;
import services.PropertyService;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController {

	@Autowired
	private PropertyService propertyService;
	
	

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Property> properties= propertyService.findAll();
		
		result=new ModelAndView("property/list");
		result.addObject("properties",properties);
		result.addObject("RequestURI", "property/list.do");
		
		return result;
	}
	

}