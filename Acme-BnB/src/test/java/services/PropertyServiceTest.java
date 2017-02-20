
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Property;
import form.PropertyForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PropertyServiceTest extends AbstractTest {

	//Services under test

	@Autowired
	private PropertyService	propertyService;


	//Create test

	//Positive
	@Test
	public void createPositiveTest() {
		authenticate("lessor1");
		PropertyForm propertyForm = propertyService.create();
		propertyForm.setAddress("test");
		propertyForm.setCapability(2);
		propertyForm.setCity("Test");
		propertyForm.setCountry("Test");
		propertyForm.setDescription("Testing");
		propertyForm.setName("Test");
		propertyForm.setProvince("Test");
		propertyForm.setRate(2.3);

		Property property = propertyService.reconstruct(propertyForm);
		propertyService.save(property);
		authenticate(null);
	}

	//Negative

	@Test
	public void createNegativeTest() {

		try {
			PropertyForm propertyForm = propertyService.create();
			propertyForm.setAddress("test");
			propertyForm.setCapability(2);
			propertyForm.setCity("Test");
			propertyForm.setCountry("Test");
			propertyForm.setDescription("Testing");
			propertyForm.setName("Test");
			propertyForm.setProvince("Test");
			propertyForm.setRate(2.3);

			Property property = propertyService.reconstruct(propertyForm);
			propertyService.save(property);
		} catch (IllegalArgumentException e) {
			System.out.println("createNegativeTest passed");

		}

		authenticate(null);
	}

	//Delete

	//Positive
	@Test
	public void deletePositiveTest() {
		authenticate("lessor1");
		PropertyForm propertyForm = propertyService.create();
		propertyForm.setAddress("test");
		propertyForm.setCapability(2);
		propertyForm.setCity("Test");
		propertyForm.setCountry("Test");
		propertyForm.setDescription("Testing");
		propertyForm.setName("Test");
		propertyForm.setProvince("Test");
		propertyForm.setRate(2.3);

		Property property = propertyService.reconstruct(propertyForm);
		Property result = propertyService.save(property);
		propertyService.delete(result);
		authenticate(null);
	}

	//Negative
	@Test
	public void deleteNegativeTest() {
		try {
			PropertyForm propertyForm = propertyService.create();
			propertyForm.setAddress("test");
			propertyForm.setCapability(2);
			propertyForm.setCity("Test");
			propertyForm.setCountry("Test");
			propertyForm.setDescription("Testing");
			propertyForm.setName("Test");
			propertyForm.setProvince("Test");
			propertyForm.setRate(2.3);

			Property property = propertyService.reconstruct(propertyForm);
			Property result = propertyService.save(property);
			propertyService.delete(result);
		} catch (IllegalArgumentException e) {
			System.out.println("deleteNegativeTest passed");
		}
	}

	//Convert to form object

	@Test
	public void conversionToFormObjectTest() {
		Property property = propertyService.findOne(18);
		PropertyForm result = propertyService.conversionToFormObject(property);
		Assert.notNull(result);
		Assert.notNull(result.getCity());
		Assert.notNull(result.getRate());
		Assert.notNull(result.getDescription());
	}
}
