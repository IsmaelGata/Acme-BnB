
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Book;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.Status;
import domain.Tenant;
import form.BookForm;
import repositories.BookRepository;

@Service
@Transactional
public class BookService {

	//Managed repository

	@Autowired
	private BookRepository			bookRepository;

	//Supported services

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private LessorService			lessorService;

	@Autowired
	private TenantService			tenantService;

	@Autowired
	private AdministratorService	administratorService;


	//Constructor

	public BookService() {
		super();
	}

	//Simple CRUD methods

	public Book create(Property property) {
		Tenant tenant = tenantService.findByPrincipal();
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

	public Book findOne(int bookId) {
		return bookRepository.findOne(bookId);

	}

	public Book save(Book book) {
		Assert.notNull(book);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(book.getTenant()));

		Book result = bookRepository.save(book);
		return result;
	}
	
	public Book update(Book book) {
		Assert.notNull(book);
		return bookRepository.save(book);
	}

	public void delete(Book book) {
		Assert.notNull(book);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(book.getTenant()));

		bookRepository.delete(book);
	}

	//Other business methods
	
	public Collection<Book> findBooksByLessorAuthenticated(){
		Lessor lessor = lessorService.findByPrincipal();
		return bookRepository.findBooksByLessorAuthenticated(lessor.getId());
	}
	
	
	public Collection<Book> findBooksByPropertyAndLessor(int propertyId){
		Lessor lessor = lessorService.findByPrincipal();
		return bookRepository.findBooksByPropertyAndLessor(propertyId, lessor.getId());
	}

	public void changeStatus(Book book, Status status) {
		Assert.notNull(book);

		Lessor lessor = lessorService.findByPrincipal();
		Property property = book.getProperty();

		Assert.notNull(property);
		Assert.isTrue(lessor.equals(property.getLessor()));

		if (status.equals(Status.ACEPTED)) {
			Assert.notNull(lessor.getCreditCard());
		}

		book.setStatus(status);
		update(book);
	}
	
	@Autowired
	Validator	validator;
	
	public Book reconstruct(BookForm bookForm, BindingResult binding) {
		Book result;
		
		Property property= propertyService.findOne(bookForm.getIdProperty());
		
		result= create(property);
		result.setCheckIn(bookForm.getCheckIn());
		result.setCheckOut(bookForm.getCheckOut());
		result.setSmoker(bookForm.isSmoker());
		result.setCreditCard(bookForm.getCreditCard());
		
		if (checkCreditCard(bookForm.getCreditCard())) {

			validator.validate(bookForm.getCreditCard(), binding);
		}
		
		return result;
	}

	//Dashboard

	public Double averageAcceptedRequestPerLessor() {
		administratorService.findByPrincipal();

		return bookRepository.averageAcceptedRequestPerLessor();
	}

	public Double averageDeniedRequestPerLessor() {
		administratorService.findByPrincipal();

		return bookRepository.averageDeniedRequestPerLessor();
	}

	public Double averageAcceptedRequestPerTenant() {
		administratorService.findByPrincipal();

		return bookRepository.averageAcceptedRequestPerTenant();
	}

	public Double averageDeniedRequestPerTenant() {
		administratorService.findByPrincipal();

		return bookRepository.averageDeniedRequestPerTenant();
	}
	
	public boolean checkCreditCard(CreditCard creditCard) {
		boolean result = true;

		if (creditCard.getBrandName().isEmpty() && creditCard.getCvv() == 0 && creditCard.getExpirationMonth() == 0 && creditCard.getExpirationYear() == 0 && creditCard.getHolderName().isEmpty() && creditCard.getNumber().isEmpty()) {
			result = false;
		}

		return result;
	}
}
