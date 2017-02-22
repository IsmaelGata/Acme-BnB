
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;
import domain.Lessor;
import domain.Tenant;
import form.TenantForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TenantServiceTest extends AbstractTest {

	//Services under test
	@Autowired
	private TenantService	tenantService;

	@Autowired
	private LessorService	lessorService;


	//Create Test

	@Test
	public void createTest() {
		TenantForm tenantForm = tenantService.create();

		tenantForm.setEmail("Testing@test.es");
		tenantForm.setName("Test");
		tenantForm.setPhone("+34 9966666666");
		tenantForm.setPicture("http://www.test.es");
		tenantForm.setSurname("Testing");
		tenantForm.setPassword("testing");
		tenantForm.setRepeatPassword("testing");
		tenantForm.setAcceptCondition(true);

		Tenant result = tenantService.reconstruct(tenantForm, null);
		tenantService.save(result);
	}
	//Find by user name

	@Test
	public void findByUserNameTest() {
		Tenant result = tenantService.findByUserName("tenant1");
		Assert.notNull(result);
	}

	// Requested Lessors By Tenant

	@Test
	public void getRequestedLessorsByTenantTest() {
		authenticate("tenant1");
		Tenant tenant = tenantService.findByPrincipal();
		Collection<Integer> result = tenantService.getRequestedLessorsByTenant(tenant);
		Assert.notNull(result);
		authenticate(null);
	}

	//Do comment test

	//Positive

	@Test
	public void doCommentPositiveTest() {
		authenticate("tenant2");
		Lessor lessor = lessorService.findOne(13);
		Comment comment = new Comment();
		comment.setMoment(new Date());
		comment.setStars(5);
		comment.setText("Testing");
		comment.setTitle("Testing");

		Comment result = tenantService.doComment(lessor, comment);
		Assert.notNull(result);
	}
	//Negative
	@Test
	public void doCommentNegativeTest() {
		authenticate("tenant2");
		Tenant tenant = tenantService.findOne(14);
		Comment comment = new Comment();
		comment.setMoment(new Date());
		comment.setStars(5);
		comment.setText("Testing");
		comment.setTitle("Testing");

		Comment result = tenantService.doComment(tenant, comment);

		Assert.isTrue(result == null);
		authenticate(null);
	}
}
