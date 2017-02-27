/* WelcomeController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Lessor;
import security.Authority;
import security.UserAccount;
import services.ActorService;
import services.LessorService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private LessorService lessorService;
	
	// Constructors -----------------------------------------------------------
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		
		double totalFee = 0.0;
		UserAccount userAccount;
		SecurityContext context;
		Authentication authentication;
		context = SecurityContextHolder.getContext();
		Object principal;
		if(context != null){
			authentication = context.getAuthentication();
			if(authentication != null){
				principal = authentication.getPrincipal();
				if(principal instanceof UserAccount){
					userAccount = (UserAccount) principal;
					Lessor lessor = lessorService.findByUserAccount(userAccount);
					if(lessor != null){
						totalFee = lessor.getTotalFee();
					}
				}
			}
		}
		
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
				
		result = new ModelAndView("welcome/index");
		result.addObject("totalFee", totalFee);
		result.addObject("moment", moment);

		return result;
	}
}