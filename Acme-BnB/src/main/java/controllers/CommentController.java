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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;
import form.CommentForm;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	//Supported services
	
	@Autowired
	private CommentService 	commentService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required=true) Integer comentableId, @RequestParam(required=true) String comentableType) {
		ModelAndView result;
		CommentForm commentForm = commentService.create(comentableId, comentableType);
		
		result = createEditModelAndView(commentForm);
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CommentForm commentForm, BindingResult binding) {
		ModelAndView result = new ModelAndView();

		if (binding.hasErrors()) {
			result = createEditModelAndView(commentForm);

		} else {
			try {
				Comment comment = commentService.reconstruct(commentForm);
				
				commentService.save(comment);
				
				result = new ModelAndView("redirect:/" + comment.getComentableType().toLowerCase() + 
						"/show.do?" + comment.getComentableType().toLowerCase() + "Id=" + comment.getComentableId());
			} catch (Throwable oops) {
				result = createEditModelAndView(commentForm, "comment.create.error");
			}
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(CommentForm commentForm) {
		ModelAndView result = createEditModelAndView(commentForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(CommentForm commentForm, String message) {
		ModelAndView result;

		result = new ModelAndView("comment/create");
		result.addObject("commentForm", commentForm);
		result.addObject("message", message);
		result.addObject("RequestURI", "comment/save.do");

		return result;
	}

}
