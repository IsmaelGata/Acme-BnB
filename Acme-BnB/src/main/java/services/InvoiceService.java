
package services;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import domain.Book;
import domain.Invoice;
import domain.Property;
import domain.Tenant;

@Service
@Transactional
public class InvoiceService {

	// Managed repository

	@Autowired
	private InvoiceRepository		invoiceRepository;

	// Supported services

	@Autowired
	private TenantService			tenantService;

	@Autowired
	private AdministratorService	administratorService;


	// Constructor

	public InvoiceService() {
		super();
	}

	// Simple CRUD methods

	public Invoice create(Book book) {
		Assert.notNull(book);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(book.getTenant()));

		Property property = book.getProperty();
		Invoice result = new Invoice();
		DateTime dt1 = new DateTime(book.getCheckIn());
		DateTime dt2 = new DateTime(book.getCheckOut());
		int days = Days.daysBetween(dt1, dt2).getDays();
		result.setTotalAmount(days * property.getRate());
		result.setVatNumber(21);
		result.setInformation("Property: " + property.getName() + ", Address: " + property.getAddress());
		result.setCreditCard(book.getCreditCard());
		result.setDetails("This invoice has been auto-generated");

		result.setBook(book);

		return result;
	}

	public Collection<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	public Invoice findOne(int invoiceId) {
		return invoiceRepository.findOne(invoiceId);

	}

	public void save(Invoice invoice) {
		Assert.notNull(invoice);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(invoice.getBook().getTenant()));
		invoice.setMoment(new Date(System.currentTimeMillis() - 1000));

		invoiceRepository.save(invoice);
	}

	@Deprecated
	public void delete(Invoice invoice) {
		Assert.notNull(invoice);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(invoice.getBook().getTenant()));

		invoiceRepository.delete(invoice);
	}

	// Other business methods

	// Dashboard

	public Integer getMaximumInvoicesIssuedToTenants() {
		administratorService.findByPrincipal();

		return invoiceRepository.getMaximumInvoicesIssuedToTenants();
	}

	public Integer getMinimumInvoicesIssuedToTenants() {
		administratorService.findByPrincipal();

		return invoiceRepository.getMinimumInvoicesIssuedToTenants();
	}

	public Double getAverageInvoicesIssuedToTenants() {
		administratorService.findByPrincipal();

		return invoiceRepository.getAverageInvoicesIssuedToTenants();
	}

	public Double getTotalAmountOfMoney() {
		administratorService.findByPrincipal();

		return invoiceRepository.getTotalAmountOfMoney();
	}

}
