
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import utilities.AbstractTest;
import domain.Book;
import domain.CreditCard;
import domain.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BookServiceTest extends AbstractTest {

	//Services under test

	@Autowired
	private BookService		bookService;

	@Autowired
	private PropertyService	propertyService;


	//Create test

	//Positive
	@Test
	public void createPositiveTest() {
		authenticate("tenant1");
		Property property = propertyService.findOne(18);
		Book book = bookService.create(property);

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("test");
		creditCard.setCvv(001);
		creditCard.setExpirationMonth(10);
		creditCard.setExpirationYear(2020);
		creditCard.setHolderName("VISA");
		creditCard.setNumber("987898789");

		book.setCheckIn(DateUtils.addDays(new Date(), 1));
		book.setCheckOut(DateUtils.addDays(new Date(), 3));
		book.setCreditCard(creditCard);
		book.setSmoker(false);

		bookService.save(book);
		authenticate(null);
	}

	//Negative
	@Test
	public void createNegativeTest() {
		try {
			Property property = propertyService.findOne(18);
			Book book = bookService.create(property);

			CreditCard creditCard = new CreditCard();
			creditCard.setBrandName("test");
			creditCard.setCvv(001);
			creditCard.setExpirationMonth(10);
			creditCard.setExpirationYear(2020);
			creditCard.setHolderName("VISA");
			creditCard.setNumber("987898789");

			book.setCheckIn(DateUtils.addDays(new Date(), 1));
			book.setCheckOut(DateUtils.addDays(new Date(), 3));
			book.setCreditCard(creditCard);
			book.setSmoker(false);

			bookService.save(book);
		} catch (IllegalArgumentException e) {
			System.out.println("createNegativeTest passed");
		}
		authenticate(null);
	}

	//Delete test

	//Positive

	@Test
	public void deletePositiveTest() {
		authenticate("tenant1");
		Property property = propertyService.findOne(18);
		Book book = bookService.create(property);

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("test");
		creditCard.setCvv(001);
		creditCard.setExpirationMonth(10);
		creditCard.setExpirationYear(2020);
		creditCard.setHolderName("VISA");
		creditCard.setNumber("987898789");

		book.setCheckIn(DateUtils.addDays(new Date(), 1));
		book.setCheckOut(DateUtils.addDays(new Date(), 3));
		book.setCreditCard(creditCard);
		book.setSmoker(false);

		Book result = bookService.save(book);
		bookService.delete(result);
		authenticate(null);
	}

	//Negative

	@Test
	public void deleteNegativeTest() {
		try {
			Property property = propertyService.findOne(18);
			Book book = bookService.create(property);

			CreditCard creditCard = new CreditCard();
			creditCard.setBrandName("test");
			creditCard.setCvv(001);
			creditCard.setExpirationMonth(10);
			creditCard.setExpirationYear(2020);
			creditCard.setHolderName("VISA");
			creditCard.setNumber("987898789");

			book.setCheckIn(DateUtils.addDays(new Date(), 1));
			book.setCheckOut(DateUtils.addDays(new Date(), 3));
			book.setCreditCard(creditCard);
			book.setSmoker(false);

			Book result = bookService.save(book);
			bookService.delete(result);
		} catch (IllegalArgumentException e) {
			System.out.println("deleteNegativeTest passed");
		}
		authenticate(null);
	}
}
