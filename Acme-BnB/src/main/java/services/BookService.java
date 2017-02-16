
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BookRepository;
import domain.Book;
import domain.Fee;
import domain.Lessor;
import domain.Property;
import domain.Status;
import domain.Tenant;

@Service
@Transactional
public class BookService {

	//Managed repository

	@Autowired
	private BookRepository	bookRepository;

	//Supported services

	@Autowired
	private LessorService	lessorService;

	@Autowired
	private TenantService	tenantService;

	@Autowired
	private FeeService		feeService;


	//Constructor

	public BookService() {
		super();
	}

	//Simple CRUD methods

	public Book create(Property property) {
		Tenant tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);
		Assert.notNull(property);

		Book result = new Book();
		result.setProperty(property);
		result.setTenant(tenant);
		result.setStatus(Status.PENDING);

		return result;
	}

	public Collection<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(int id_book) {
		return bookRepository.findOne(id_book);

	}

	public Book save(Book book) {
		Assert.notNull(book);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(book.getTenant()));

		Book result = bookRepository.save(book);
		return result;
	}

	public void delete(Book book) {
		Assert.notNull(book);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(book.getTenant()));

		bookRepository.delete(book);
	}

	//Other business methods

	public void changeStatus(Book book, Status status) {
		Assert.notNull(book);

		Lessor lessor = lessorService.findByPrincipal();
		Property property = book.getProperty();

		Assert.notNull(property);
		Assert.isTrue(lessor.equals(property.getLessor()));

		if (status.equals(Status.ACEPTED)) {
			List<Fee> fee = new ArrayList<Fee>(feeService.findAll());
			Assert.isTrue(!fee.isEmpty());
			lessor.setPaid(lessor.getPaid() + fee.get(0).getAmount());

			lessorService.save(lessor);
		}
		book.setStatus(status);
		save(book);
	}
}
