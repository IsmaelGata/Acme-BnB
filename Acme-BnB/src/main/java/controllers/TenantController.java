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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Lessor;
import domain.Tenant;
import form.TenantForm;
import services.LessorService;
import services.TenantService;

@Controller
@RequestMapping("/tenant")
public class TenantController extends AbstractController {

	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private LessorService lessorService;

	// Listing
	@RequestMapping(value = "/listByLessor", method = RequestMethod.GET)
	public ModelAndView list(@Valid int lessorId) {
		ModelAndView result;
		Lessor lessor = lessorService.findOne(lessorId);
		Collection<Tenant> tenants = lessorService.getRequestersTenantsWithoutIdByLessor(lessor);
		result = new ModelAndView("tenant/list");
		result.addObject("tenants", tenants);
		result.addObject("RequestURI", "tenant/listByLessor.do?lessorId="+lessorId);

		return result;
	}
	
	// Listing
//		@RequestMapping(value = "/commentDo", method = RequestMethod.GET)
//		public ModelAndView list(@Valid int lessorId) {
//			ModelAndView result;
//			Lessor lessor = lessorService.findOne(lessorId);
//			Collection<Tenant> tenants = lessorService.getRequestersTenantsWithoutIdByLessor(lessor);
//			result = new ModelAndView("tenant/list");
//			result.addObject("tenants", tenants);
//			result.addObject("RequestURI", "tenant/listByLessor.do?lessorId="+lessorId);
//
//			return result;
//		}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		TenantForm tenantForm = new TenantForm();
		result = createEditModelAndView(tenantForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TenantForm tenantForm, BindingResult binding) {
		ModelAndView result;
		Tenant tenant;

		tenant = tenantService.reconstruct(tenantForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(tenantForm);
		} else {
			try {
				tenantService.save(tenant);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(tenantForm, "tenant.commit.error");
			}
		}

		return result;
	}

	// Ancillary methods

	protected ModelAndView createEditModelAndView(TenantForm tenantForm) {
		ModelAndView result = createEditModelAndView(tenantForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(TenantForm tenantForm, String message) {
		ModelAndView result;

		result = new ModelAndView("tenant/register");
		result.addObject("tenantForm", tenantForm);
		result.addObject("message", message);

		return result;
	}

}