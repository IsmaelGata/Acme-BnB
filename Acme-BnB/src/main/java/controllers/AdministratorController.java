/* AdministratorController.java
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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Lessor;
import domain.Property;
import domain.Tenant;
import services.BookService;
import services.FinderService;
import services.InvoiceService;
import services.LessorService;
import services.PropertyService;
import services.SocialIdentityService;
import services.TenantService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private LessorService lessorService;

	@Autowired
	private BookService bookService;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private FinderService finderService;

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private SocialIdentityService socialIdentityService;
	
	@Autowired
	private InvoiceService invoiceService;

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Dashboard C
	@RequestMapping(value = "/dashboardC", method = RequestMethod.GET)
	public ModelAndView dashboardC() {
		ModelAndView result;

		Double averageAcceptedRequestPerLessor = bookService.averageAcceptedRequestPerLessor();
		Double averageDeniedRequestPerLessor = bookService.averageDeniedRequestPerLessor();
		Object[] averageLessor = { averageAcceptedRequestPerLessor, averageDeniedRequestPerLessor };

		Double averageAcceptedRequestPerTenant = bookService.averageAcceptedRequestPerTenant();
		Double averageDeniedRequestPerTenant = bookService.averageDeniedRequestPerTenant();
		Object[] averageTenant = { averageAcceptedRequestPerTenant, averageDeniedRequestPerTenant };

		Collection<Lessor> lessorsWithMoreAcceptedRequests = lessorService.getLessorsWithMoreAcceptedRequests();
		Collection<Lessor> lessorsWithMoreDeniedRequests = lessorService.getLessorsWithMoreDeniedRequests();
		Collection<Lessor> lessorsWithMorePendingRequests = lessorService.getLessorsWithMorePendingRequests();
		Collection<Tenant> tenantsWithMoreAcceptedRequests = tenantService.getTenantsWithMoreAcceptedRequests();
		Collection<Tenant> tenantsWithMoreDeniedRequests = tenantService.getTenantsWithMoreDeniedRequests();
		Collection<Tenant> tenantsWithMorePendingRequests = tenantService.getTenantsWithMorePendingRequests();
		Collection<Lessor> lessorsWithMaximumRatioOfApprovedRequests = lessorService
				.getLessorsWithMaximumRatioOfApprovedRequests();
		Collection<Lessor> lessorsWithMinimumRatioOfApprovedRequests = lessorService
				.getLessorsWithMinimumRatioOfApprovedRequests();
		Collection<Tenant> tenantsWithMaximumRatioOfApprovedRequests = tenantService
				.getTenantsWithMaximumRatioOfApprovedRequests();
		Collection<Tenant> tenantsWithMinimumRatioOfApprovedRequests = tenantService
				.getTenantsWithMinimumRatioOfApprovedRequests();
		Double averageResultsPerFinder = finderService.getAverageResultsPerFinder();
		Integer minimumResultsPerFinder = finderService.getMinimumResultsPerFinder();
		Integer maximumResultsPerFinder = finderService.getMaximumResultsPerFinder();

		Object[] stadistictsPerFinder = { averageResultsPerFinder, minimumResultsPerFinder, maximumResultsPerFinder };

		result = new ModelAndView("administrator/dashboardC");
		result.addObject("averageLessor", averageLessor);
		result.addObject("averageTenant", averageTenant);
		result.addObject("lessorsWithMoreAcceptedRequests", lessorsWithMoreAcceptedRequests);
		result.addObject("lessorsWithMoreDeniedRequests", lessorsWithMoreDeniedRequests);
		result.addObject("lessorsWithMorePendingRequests", lessorsWithMorePendingRequests);
		result.addObject("tenantsWithMoreAcceptedRequests", tenantsWithMoreAcceptedRequests);
		result.addObject("tenantsWithMoreDeniedRequests", tenantsWithMoreDeniedRequests);
		result.addObject("tenantsWithMorePendingRequests", tenantsWithMorePendingRequests);
		result.addObject("lessorsWithMaximumRatioOfApprovedRequests", lessorsWithMaximumRatioOfApprovedRequests);
		result.addObject("lessorsWithMinimumRatioOfApprovedRequests", lessorsWithMinimumRatioOfApprovedRequests);
		result.addObject("tenantsWithMaximumRatioOfApprovedRequests", tenantsWithMaximumRatioOfApprovedRequests);
		result.addObject("tenantsWithMinimumRatioOfApprovedRequests", tenantsWithMinimumRatioOfApprovedRequests);
		result.addObject("stadistictsPerFinder", stadistictsPerFinder);

		return result;
	}

	// Dashboard B

	@RequestMapping(value = "/dashboardB", method = RequestMethod.GET)
	public ModelAndView dashboardB() {
		ModelAndView result;
		Double averageAuditsPerProperty = propertyService.getAverageAuditsPerProperty();
		Integer minimumAuditsPerProperty = propertyService.getMinimumAuditsPerProperty();
		Integer maximumAuditsPerProperty = propertyService.getMaximumAuditsPerProperty();
		Object[] stadistictsPerProperty = { averageAuditsPerProperty, minimumAuditsPerProperty,
				maximumAuditsPerProperty };
		// falta uno
		Map<String, Collection<Property>> propertyOrderAudits = propertyService.getPropertyOrderAudits();
		Map<String, Collection<Property>> propertyOrderBook = propertyService.getPropertyOrderBook();
		Map<String, Collection<Property>> propertyOrderBookAcepted = propertyService.getPropertyOrderBookAcepted();
		Map<String, Collection<Property>> propertyOrderBookDenied = propertyService.getPropertyOrderBookDenied();
		Map<String, Collection<Property>> propertyOrderBookPending = propertyService.getPropertyOrderBookPending();

		result = new ModelAndView("administrator/dashboardB");
		result.addObject("stadistictsPerProperty", stadistictsPerProperty);
		result.addObject("propertyOrderAudits", propertyOrderAudits);
		result.addObject("propertyOrderBook", propertyOrderBook);
		result.addObject("propertyOrderBookAcepted", propertyOrderBookAcepted);
		result.addObject("propertyOrderBookDenied", propertyOrderBookDenied);
		result.addObject("propertyOrderBookPending", propertyOrderBookPending);
		return result;
	}

	// Dashboard A

	@RequestMapping(value = "/dashboardA", method = RequestMethod.GET)
	public ModelAndView dashboardA() {
		ModelAndView result;
		Double averageSocialIdentitiesPerActor = socialIdentityService.getAverageSocialIdentitiesPerActor();
		Integer minimumSocialIdentitiesPerActor = socialIdentityService.getMinimumSocialIdentitiesPerActor();
		Integer maximumSocialIdentitiesPerActor = socialIdentityService.getMaximumSocialIdentitiesPerActor();
		Object[] stadistictsSocialIdentityPerActor = { averageSocialIdentitiesPerActor, minimumSocialIdentitiesPerActor, maximumSocialIdentitiesPerActor};
		
		Double averageInvoicesIssuedToTenants = invoiceService.getAverageInvoicesIssuedToTenants();
		Integer minimumInvoicesIssuedToTenants = invoiceService.getMinimumInvoicesIssuedToTenants();
		Integer maximumInvoicesIssuedToTenants = invoiceService.getMaximumInvoicesIssuedToTenants();
		Object[] stadistictsInvoicePerTenants = { averageInvoicesIssuedToTenants, minimumInvoicesIssuedToTenants, maximumInvoicesIssuedToTenants};
		
		Double totalAmountOfMoney = invoiceService.getTotalAmountOfMoney();
		Double avgNumRequestWithAuditVsWithoutAudit = bookService.avgNumRequestWithAuditVsWithoutAudit();
		
		result = new ModelAndView("administrator/dashboardA");
		result.addObject("stadistictsSocialIdentityPerActor", stadistictsSocialIdentityPerActor);
		result.addObject("stadistictsInvoicePerTenants", stadistictsInvoicePerTenants);
		result.addObject("totalAmountOfMoney", totalAmountOfMoney);
		result.addObject("avgNumRequestWithAuditVsWithoutAudit", avgNumRequestWithAuditVsWithoutAudit);
		return result;
	}

}