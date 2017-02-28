
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Book;
import domain.Status;
import domain.Tenant;
import form.BookForm;
import services.BookService;
import services.TenantService;

@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {

	@Autowired
	private BookService	bookService;
	
	@Autowired
	private TenantService tenantService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId) {
		ModelAndView result;
		BookForm bookForm = new BookForm();

		bookForm.setIdProperty(propertyId);

		result = createEditModelAndView(bookForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid BookForm bookForm, BindingResult binding) {
		ModelAndView result;
		Book book;

		book = bookService.reconstruct(bookForm, binding);
		if (binding.hasErrors()) {
			if (bookService.checkCreditCardBindingErrors(binding.getAllErrors().toString())) {
				result = createEditModelAndView(bookForm, "book.creditCard.error");
			} else {
				result = createEditModelAndView(bookForm);
			}

		} else {
			try {
				Boolean var = LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(bookForm.getCreditCard().getNumber());
				if (var == false) {
					throw new IllegalArgumentException("invalid credit card number");
				}
				bookService.save(book);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(bookForm, "book.commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/listBooksTenant", method = RequestMethod.GET)
	public ModelAndView listBooksTenant() {
		ModelAndView result;
		Tenant tenant= tenantService.findByPrincipal();

		result = new ModelAndView("book/list");
		result.addObject("books", tenant.getBooks());
		result.addObject("RequestURI", "book/listBooksTenant.do");

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Book> books = bookService.findBooksByLessorAuthenticated();

		result = listModelAndView(books, null);

		return result;
	}

	@RequestMapping(value = "/booksByPorperty", method = RequestMethod.GET)
	public ModelAndView listByProperty(@Valid int propertyId) {
		ModelAndView result;
		Collection<Book> books = bookService.findBooksByPropertyAndLessor(propertyId);

		result = listModelAndView(books, null);

		return result;
	}

	@RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
	public ModelAndView changeStatus(@Valid int bookId, @Valid String value) {
		ModelAndView result;
		Book book = bookService.findOne(bookId);
		Collection<Book> books = bookService.findBooksByLessorAuthenticated();
		try {
			Status status = null;
			if ("ACEPTED".equals(value)) {
				status = Status.ACEPTED;
			} else if ("DENIED".equals(value)) {
				status = Status.DENIED;
			}

			if (status == null) {
				throw new IllegalArgumentException();
			}
			bookService.changeStatus(book, status);
			result = new ModelAndView("redirect:/book/list.do");
		} catch (IllegalArgumentException e) {
			result = listModelAndView(books, "book.commit.error.status");
		} catch (Throwable oops) {
			result = listModelAndView(books, "book.commit.error");
		}

		return result;
	}

	protected ModelAndView listModelAndView(Collection<Book> books, String message) {
		ModelAndView result;

		result = new ModelAndView("book/list");
		result.addObject("books", books);
		if (message != null) {
			result.addObject("message", message);
		}
		result.addObject("RequestURI", "book/list.do");

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(BookForm bookForm) {
		ModelAndView result = createEditModelAndView(bookForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(BookForm bookForm, String message) {
		ModelAndView result;

		result = new ModelAndView("book/create");
		result.addObject("bookForm", bookForm);
		result.addObject("message", message);
		result.addObject("RequestURI", "book/create.do");

		return result;
	}

}
