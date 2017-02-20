
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import utilities.AbstractTest;
import domain.Auditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AuditorServiceTest extends AbstractTest {

	//Services under test

	@Autowired
	private AuditorService	auditorService;


	//Create
	@Test
	public void createTest() {
		authenticate("admin");
		Auditor result = auditorService.create();

		result.setEmail("Testing@test.es");
		result.setName("Test");
		result.setPhone("+34 666666666");
		result.setPicture("http://www.test.es");
		result.setSurname("Testing");
		result.setCompanyName("Testiong AS");

		auditorService.save(result);
		authenticate(null);
	}
}
