
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import utilities.AbstractTest;
import domain.Comment;
import domain.CreditCard;
import domain.Lessor;
import domain.Tenant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LessorServiceTest extends AbstractTest {

	//Services under test
	@Autowired
	private LessorService	lessorService;

	@Autowired
	private TenantService	tenantService;


	//Create test

	@Test
	public void createTest() {

		Lessor result = lessorService.create();
		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("test");
		creditCard.setCvv(001);
		creditCard.setExpirationMonth(10);
		creditCard.setExpirationYear(2020);
		creditCard.setHolderName("VISA");
		creditCard.setNumber("987898789");

		result.setEmail("Testing@test.es");
		result.setName("Test");
		result.setPhone("+34 666666666");
		result.setPicture("http://www.test.es");
		result.setSurname("Testing");
		result.setCreditCard(creditCard);

		lessorService.save(result);
		authenticate(null);
	}

	// Total Ammount to pay

	//Positive test
	@Test
	public void calculateTotalAmountPositiveTest() {
		authenticate("lessor1");
		System.out.println(lessorService.calculateTotalAmount());
		authenticate(null);
	}

	//Negative test
	@Test
	public void calculateTotalAmountNegativeTest() {
		authenticate(null);
		try {
			System.out.println(lessorService.calculateTotalAmount());
		} catch (Throwable e) {
			System.out.println("calculateTotalAmountNegativeTest passed");
		}

		authenticate(null);
	}

	//Doing comment

	//Positive test
	@Test
	public void doCommentPositiveTest() {
		authenticate("lessor1");
		Tenant tenant = tenantService.findOne(14);
		Comment comment = new Comment();
		comment.setMoment(new Date());
		comment.setStars(5);
		comment.setText("Testing");
		comment.setTitle("Testing");

		lessorService.doComment(tenant, comment);
		authenticate(null);
	}

	//Negative test
	@Test
	public void doCommentNegativeTest() {
		authenticate(null);

		try {
			Tenant tenant = tenantService.findOne(14);
			Comment comment = new Comment();
			comment.setMoment(new Date());
			comment.setStars(5);
			comment.setText("Testing");
			comment.setTitle("Testing");

			lessorService.doComment(tenant, comment);
		} catch (Throwable e) {
			System.out.println("doCommentNegativeTest passed");
		}

		authenticate(null);
	}
}
