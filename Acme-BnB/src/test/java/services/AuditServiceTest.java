
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import utilities.AbstractTest;
import domain.Audit;
import domain.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AuditServiceTest extends AbstractTest {

	//Services under test

	@Autowired
	private AuditService	auditService;

	@Autowired
	private PropertyService	propertyService;


	//Create test

	//Positive test

	@Test
	public void createPositiveTest() {
		authenticate("auditor1");
		Property property = propertyService.findOne(18);
		Audit audit = auditService.create(property);
		audit.setDraft(true);
		audit.setText("Testing");
		audit.setAttachments("test1, test2");

		auditService.save(audit);
		authenticate(null);
	}

	//Negative

	@Test
	public void createNegativeTest() {
		try {
			Property property = propertyService.findOne(18);
			Audit audit = auditService.create(property);
			audit.setDraft(true);
			audit.setText("Testing");
			audit.setAttachments("test1, test2");

			auditService.save(audit);
			authenticate(null);
		} catch (IllegalArgumentException e) {
			System.out.println("createNegativeTest passed");
		}

	}

	//Delete test

	//Positive

	@Test
	public void deletePositiveTest() {
		authenticate("auditor1");
		Property property = propertyService.findOne(18);
		Audit audit = auditService.create(property);
		audit.setDraft(true);
		audit.setText("Testing");
		audit.setAttachments("test1, test2");

		auditService.save(audit);
		auditService.delete(audit);
		authenticate(null);
	}

	//Negative

	@Test
	public void deleteNegativeTest() {

		try {
			Property property = propertyService.findOne(18);
			Audit audit = auditService.create(property);
			audit.setDraft(true);
			audit.setText("Testing");
			audit.setAttachments("test1, test2");

			auditService.save(audit);
			auditService.delete(audit);

		} catch (IllegalArgumentException e) {
			System.out.println("deleteNegativeTest passed");
		}
		authenticate(null);
	}
}
