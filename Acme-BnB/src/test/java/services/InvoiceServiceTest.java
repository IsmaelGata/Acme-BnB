
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import utilities.AbstractTest;
import domain.Book;
import domain.Invoice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InvoiceServiceTest extends AbstractTest {

	//Service under Test

	@Autowired
	private InvoiceService	invoiceService;

	@Autowired
	private BookService		bookService;


	//Create test

	//Positive
	@Test
	public void createPositiveTest() {
		authenticate("tenant1");
		Book book = bookService.findOne(33);
		Invoice invoice = invoiceService.create(book);

		invoiceService.save(invoice);
		authenticate(null);
	}

	//Negative
	@Test
	public void createNegativeTest() {
		try {

			Book book = bookService.findOne(33);
			Invoice invoice = invoiceService.create(book);

			invoiceService.save(invoice);
		} catch (IllegalArgumentException e) {
			System.out.println("createNegativeTest passed");
		}

	}
}
