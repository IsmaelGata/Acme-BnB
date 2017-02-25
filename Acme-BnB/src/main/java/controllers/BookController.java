package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Book;
import domain.Status;
import form.LessorForm;
import services.BookService;

@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Book> books = bookService.findBooksByLessorAuthenticated();

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
			}else if ("DENIED".equals(value)) {
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

}
