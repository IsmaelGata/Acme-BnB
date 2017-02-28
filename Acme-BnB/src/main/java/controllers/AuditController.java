
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

import services.AuditService;
import services.AuditorService;
import services.PropertyService;
import domain.Audit;
import domain.Auditor;
import form.AuditForm;

@Controller
@RequestMapping("/audit")
public class AuditController extends AbstractController {

	//Supported services

	@Autowired
	private AuditService	auditService;

	@Autowired
	private PropertyService	propertyService;

	@Autowired
	private AuditorService	auditorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId, @RequestParam(required = false) String editErrorMessage, @RequestParam(required = false) String deleteErrorMessage) {
		ModelAndView result;

		try {
			Auditor auditor = auditorService.findByPrincipal();
			Collection<Audit> audits = propertyService.findOne(propertyId).getAudits();
			result = new ModelAndView("audit/list");
			result.addObject("audits", audits);
			result.addObject("RequestURI", "audit/list.do");
			result.addObject("editErrorMessage", editErrorMessage);
			result.addObject("deleteErrorMessage", deleteErrorMessage);
			result.addObject("auditorId", auditor.getId());

		} catch (Throwable e) {
			Collection<Audit> audits = propertyService.findOne(propertyId).getAudits();
			result = new ModelAndView("audit/list");
			result.addObject("audits", audits);
			result.addObject("RequestURI", "audit/list.do");
		}

		return result;
	}

	//Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId) {
		ModelAndView result;

		try {
			Assert.isTrue(auditService.countPropertyAuditor(auditorService.findByPrincipal().getId(), propertyId) == 0);
			AuditForm auditForm = new AuditForm();

			auditForm.setIdProperty(propertyId);

			result = createEditModelAndView(auditForm);

		} catch (Throwable e) {
			result = new ModelAndView("redirect:/property/list.do");
			result.addObject("createAuditError", "property.create.audit");
		}

		return result;
	}

	//Save

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuditForm auditForm, BindingResult binding) {
		ModelAndView result;
		Audit audit;

		audit = auditService.reconstruct(auditForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(auditForm);
		} else {
			try {
				auditService.save(audit);
				result = new ModelAndView("redirect:/audit/list.do?propertyId=" + auditForm.getIdProperty());
			} catch (Throwable oops) {
				result = createEditModelAndView(auditForm, "audit.commit.error");
			}
		}

		return result;
	}

	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int auditId) {
		ModelAndView result;
		Audit audit = auditService.findOne(auditId);

		try {
			Assert.isTrue(audit.getDraft() == true);
			Auditor auditor = auditorService.findByPrincipal();
			Assert.isTrue(auditor.equals(audit.getAuditor()));
			AuditForm auditForm = auditService.convertoToFormObject(audit);

			result = createEditModelAndView(auditForm);
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/audit/list.do");
			result.addObject("editErrorMessage", "audit.edit.error");
			result.addObject("propertyId", audit.getProperty().getId());
		}

		return result;
	}

	//Delete

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int auditId) {
		ModelAndView result;
		Audit audit = auditService.findOne(auditId);

		try {

			Assert.isTrue(audit.getDraft() == true);
			auditService.delete(audit);

			result = new ModelAndView("redirect:/audit/list.do");
			result.addObject("propertyId", audit.getProperty().getId());
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/audit/list.do");
			result.addObject("propertyId", audit.getProperty().getId());
			result.addObject("deleteErrorMessage", "audit.delete.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(AuditForm auditForm) {
		ModelAndView result = createEditModelAndView(auditForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(AuditForm auditForm, String message) {
		ModelAndView result;

		result = new ModelAndView("audit/create");
		result.addObject("auditForm", auditForm);
		result.addObject("message", message);
		result.addObject("RequestURI", "audit/save.do");

		return result;
	}

}
