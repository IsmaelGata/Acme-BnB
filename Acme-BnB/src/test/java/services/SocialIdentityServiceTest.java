
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
import domain.SocialIdentity;
import form.SocialIdentityForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SocialIdentityServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private SocialIdentityService	socialIdentityService;


	//Create test

	//Positive
	@Test
	public void createTest() {
		authenticate("tenant1");
		SocialIdentityForm socialIdentityForm = socialIdentityService.create();
		socialIdentityForm.setNameSocialNetwork("Test");
		socialIdentityForm.setNick("Test");
		socialIdentityForm.setURL("http://www.testing.es");
		SocialIdentity result = socialIdentityService.reconstruct(socialIdentityForm);
		socialIdentityService.save(result);

		authenticate(null);
	}

	//Negative

	@Test
	public void createNegativeTest() {
		try {
			SocialIdentityForm socialIdentityForm = socialIdentityService.create();
			socialIdentityForm.setNameSocialNetwork("Test");
			socialIdentityForm.setNick("Test");
			socialIdentityForm.setURL("http://www.testing.es");
			SocialIdentity result = socialIdentityService.reconstruct(socialIdentityForm);
			socialIdentityService.save(result);

		} catch (IllegalArgumentException e) {
			System.out.println("createNegativeTest passed");
		}
	}

	//Delete test

	//Positive
	@Test
	public void deletePositiveTest() {
		authenticate("tenant1");
		SocialIdentityForm socialIdentityForm = socialIdentityService.create();
		socialIdentityForm.setNameSocialNetwork("Test");
		socialIdentityForm.setNick("Test");
		socialIdentityForm.setURL("http://www.testing.es");
		SocialIdentity result2 = socialIdentityService.reconstruct(socialIdentityForm);
		SocialIdentity result = socialIdentityService.save(result2);
		socialIdentityService.delete(result);
		authenticate(null);
	}

	//Negative
	@Test
	public void deleteNegativeTest() {

		try {
			SocialIdentityForm socialIdentityForm = socialIdentityService.create();
			socialIdentityForm.setNameSocialNetwork("Test");
			socialIdentityForm.setNick("Test");
			socialIdentityForm.setURL("http://www.testing.es");
			SocialIdentity result2 = socialIdentityService.reconstruct(socialIdentityForm);
			SocialIdentity result = socialIdentityService.save(result2);
			socialIdentityService.delete(result);
		} catch (IllegalArgumentException e) {
			System.out.println("deleteNegativeTest passed");
		}
		authenticate(null);
	}

	//Convert to form object

	@Test
	public void conversionToFormObjectTest() {
		SocialIdentity socialIdentity = socialIdentityService.findOne(45);
		SocialIdentityForm result = socialIdentityService.conversionToFormObject(socialIdentity);
		Assert.notNull(result);
		Assert.notNull(result.getNameSocialNetwork());
		Assert.notNull(result.getURL());
		Assert.notNull(result.getNick());

	}
}
