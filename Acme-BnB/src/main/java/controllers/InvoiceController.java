package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Book;
import domain.Invoice;
import services.BookService;
import services.InvoiceService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController extends AbstractController{

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping(value = "/issueInvoice", method = RequestMethod.GET)
	public ModelAndView issueInvoice(@Valid int bookId){
		ModelAndView result;
		Book book = bookService.findOne(bookId);
		Invoice invoice = invoiceService.create(book);
		invoiceService.save(invoice);
		result = createEditModelAndView(invoice);
		
		return result;
	}
	
	@RequestMapping(value = "/showInvoice", method = RequestMethod.GET)
	public ModelAndView showInvoice(@Valid int invoiceId){
		ModelAndView result;
		Invoice invoice = invoiceService.findOne(invoiceId);
		result = createEditModelAndView(invoice);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Invoice invoice) {
		ModelAndView result = createEditModelAndView(invoice, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Invoice invoice, String message) {
		ModelAndView result;

		result = new ModelAndView("invoice/create");
		result.addObject("invoice", invoice);
		result.addObject("message", message);
		result.addObject("RequestURI", "invoice/create.do");

		return result;
	}
}
