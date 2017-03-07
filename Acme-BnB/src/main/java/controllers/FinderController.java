
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.TenantService;
import domain.Finder;
import domain.Tenant;
import form.FinderForm;

@Controller
@RequestMapping("/finder")
public class FinderController extends AbstractController {

	@Autowired
	private FinderService	finderService;

	@Autowired
	private TenantService	tenantService;


	@RequestMapping(value = "/configure", method = RequestMethod.GET)
	public ModelAndView configure() {
		ModelAndView result;
		Tenant tenant = tenantService.findByPrincipal();
		FinderForm finderForm = finderService.convertoToFormObject(tenant.getFinder());
		result = createEditModelAndView(finderForm);
		result.addObject("properties", finderForm.getListProperties());

		return result;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FinderForm finderForm, BindingResult binding) {
		ModelAndView result;
		Finder finder = new Finder();

		finder = finderService.reconstruct(finderForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(finderForm);
		} else if (finder.getMaximum() < finder.getMinimun()) {
			result = createEditModelAndView(finderForm, "finder.commit.error");
		} else {
			try {
				finder = finderService.finderEngine(finder);
				finderService.save(finder);
				result = new ModelAndView("finder/configure");
				result.addObject("properties", finder.getListProperty());
				//				result.addObject("RequestURI", "finder/configure.do");

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

		result = new ModelAndView("finder/configure");
		result.addObject("finderForm", finderForm);
		result.addObject("message", message);
		result.addObject("RequestURI", "finder/find.do");
		return result;
	}

}
