
package services;

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
import domain.Tenant;
import form.CommentForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CommentServiceTest extends AbstractTest {

	//Services under test

	@Autowired
	private CommentService	commentService;

	@Autowired
	private TenantService	tenantService;


	//Create test
	@Test
	public void createTest() {
		Comment comment = new Comment();
		authenticate("tenant1");
		Tenant tenant = tenantService.findByPrincipal();
		comment.setAuthor(tenant);
		comment.setComentableId(1);
		comment.setComentableType("Test");
		comment.setMoment(new Date());
		comment.setStars(2);
		comment.setText("Testing");
		comment.setTitle("Test");

		commentService.save(comment);
		authenticate(null);
	}

	//Conversion to form object test

	@Test
	public void conversionToFormObjectTest() {

		Comment comment = new Comment();
		authenticate("tenant1");
		Tenant tenant = tenantService.findByPrincipal();
		comment.setAuthor(tenant);
		comment.setComentableId(1);
		comment.setComentableType("Test");
		comment.setMoment(new Date());
		comment.setStars(2);
		comment.setText("Testing");
		comment.setTitle("Test");

		CommentForm commentForm = commentService.conversionToFormObject(comment);
		Assert.notNull(commentForm);

		authenticate(null);
	}

	//Reconstruct from form object test

	@Test
	public void reconstructTest() {

		CommentForm commentForm = new CommentForm();
		authenticate("tenant1");
		commentForm.setComentableId(1);
		commentForm.setStars(2);
		commentForm.setText("Testing");
		commentForm.setTitle("Test");

		Comment result = commentService.reconstruct(commentForm);
		System.out.println(result);
		Assert.notNull(commentForm);

		authenticate(null);
	}

}
