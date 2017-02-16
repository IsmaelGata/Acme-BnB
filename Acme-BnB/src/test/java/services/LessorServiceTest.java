
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
import domain.Tenant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class LessorServiceTest extends AbstractTest {

	//Services under test
	@Autowired
	private LessorService	lessorService;

	@Autowired
	private TenantService	tenantService;


	//Doing comment

	@Test
	public void doCommentTest() {
		authenticate("lessor1");
		Tenant tenant = tenantService.findOne(14);
		Comment comment = new Comment();
		comment.setMoment(new Date());
		comment.setStars(5);
		comment.setText("Testing");
		comment.setTitle("Testing");

		Comment result = lessorService.doComment(tenant, comment);
		System.out.println(result);
	}
}
