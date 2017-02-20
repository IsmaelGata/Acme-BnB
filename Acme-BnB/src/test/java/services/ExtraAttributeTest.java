
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import utilities.AbstractTest;
import domain.ExtraAttribute;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ExtraAttributeTest extends AbstractTest {

	//Services under test

	@Autowired
	private ExtraAttributeService	extraAttributeService;


	//Create test

	//Positive
	@Test
	public void createTest() {
		authenticate("admin");
		ExtraAttribute extraAttribute = extraAttributeService.create();
		extraAttribute.setIsBoolean(false);
		extraAttribute.setIsNumber(false);
		extraAttribute.setName("Test");
		extraAttribute.setValue("Test");

		extraAttributeService.save(extraAttribute);
		authenticate(null);
	}

	//Negative
	@Test
	public void createNegativeTest() {
		try {
			ExtraAttribute extraAttribute = extraAttributeService.create();
			extraAttribute.setIsBoolean(false);
			extraAttribute.setIsNumber(false);
			extraAttribute.setName("Test");
			extraAttribute.setValue("Test");

			extraAttributeService.save(extraAttribute);
		} catch (IllegalArgumentException e) {
			System.out.println("createNegativeTest passed");
		}
	}

	//Delete test

	//Positive

	@Test
	public void deletePositiveTest() {
		authenticate("admin");
		ExtraAttribute extraAttribute = extraAttributeService.create();
		extraAttribute.setIsBoolean(false);
		extraAttribute.setIsNumber(false);
		extraAttribute.setName("Test");
		extraAttribute.setValue("Test");

		ExtraAttribute result = extraAttributeService.save(extraAttribute);
		extraAttributeService.delete(result);

		authenticate(null);
	}

	//Negative
	@Test
	public void deleteNegativeTest() {
		try {
			ExtraAttribute extraAttribute = extraAttributeService.create();
			extraAttribute.setIsBoolean(false);
			extraAttribute.setIsNumber(false);
			extraAttribute.setName("Test");
			extraAttribute.setValue("Test");

			ExtraAttribute result = extraAttributeService.save(extraAttribute);
			extraAttributeService.delete(result);
		} catch (IllegalArgumentException e) {
			System.out.println("deleteNegativeTest passed");
		}

		authenticate(null);
	}
}
