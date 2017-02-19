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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Tenant;
import form.TenantForm;
import services.TenantService;

@Controller
@RequestMapping("/tenant")
public class TenantController extends AbstractController {

	@Autowired
	private TenantService tenantService;
	
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView result;
		TenantForm tenantForm= new TenantForm();
		result = createEditModelAndView(tenantForm);
		
		return result;
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		Tenant tenant= tenantService.findByPrincipal();
		result = createEditModelAndView(tenant);
		
		return result;
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Tenant tenant, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result= createEditModelAndView(tenant);
		}else{
			try{
				tenantService.save(tenant);
				result= createEditModelAndView(tenant);
			}catch(Throwable oops){
				result= createEditModelAndView(tenant,"tenant.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST,params="save")
	public ModelAndView save(@Valid TenantForm tenantForm, BindingResult binding){
		ModelAndView result;
		Tenant tenant;
		
		tenant=tenantService.reconstruct(tenantForm,binding);
		if(binding.hasErrors()){
			result= createEditModelAndView(tenant);
		}else{
			try{
				tenantService.save(tenant);
				result= new ModelAndView("redirect:/security/login.do");
			}catch(Throwable oops){
				result= createEditModelAndView(tenantForm,"tenant.commit.error");
			}
		}
		
		return result;
	}
	
	
	//Ancillary methods
	
	protected ModelAndView createEditModelAndView(TenantForm tenantForm){
		ModelAndView result=createEditModelAndView(tenantForm,null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(TenantForm tenantForm,String message){
		ModelAndView result;
		
		result= new ModelAndView("tenant/register");
		result.addObject("tenantForm", tenantForm);
		result.addObject("message", message);
		
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Tenant tenant){
		ModelAndView result=createEditModelAndView(tenant,null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Tenant tenant,String message){
		ModelAndView result;
		
		result= new ModelAndView("tenant/edit");
		result.addObject("tenant", tenant);
		result.addObject("message", message);
		
		
		return result;
	}
	

}