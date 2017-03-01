
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import domain.Finder;
import form.FinderForm;

@Controller
@RequestMapping("/finder")
public class FinderController extends AbstractController {

	@Autowired
	private FinderService	finderService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		FinderForm finderForm = new FinderForm();
		result = createEditModelAndView(finderForm);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FinderForm finderForm, BindingResult binding) {
		ModelAndView result;
		Finder finder = new Finder();

		//		finder = finderService.reconstruct(finderForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(finderForm);
		} else {
			try {
				finder = finderService.finderEngine(finderForm.getDestination(), finderForm.getMinimun(), finderForm.getMaximum(), finderForm.getKeyWord());
				finderService.save(finder);
				result = new ModelAndView("property/list");
				result.addObject("properties", finder.getListProperty());
				result.addObject("RequestURI", "property/list.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(finderForm, "finder.commit.error");
			}
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(FinderForm finderForm) {
		ModelAndView result = createEditModelAndView(finderForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(FinderForm finderForm, String message) {
		ModelAndView result;

		result = new ModelAndView("finder/register");
		result.addObject("finderForm", finderForm);
		result.addObject("message", message);
		result.addObject("RequestURI", "finder/save.do");
		return result;
	}

}
