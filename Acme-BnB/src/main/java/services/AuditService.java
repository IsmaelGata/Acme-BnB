
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRepository;
import domain.Audit;
import domain.Auditor;
import domain.Property;

@Service
@Transactional
public class AuditService {

	//Managed repository

	@Autowired
	private AuditRepository	auditRepository;

	//Supported services

	@Autowired
	private AuditorService	auditorService;


	//Constructor

	public AuditService() {
		super();
	}

	//Simple CRUD methods

	public Audit create(Property property) {
		Assert.notNull(property);
		Audit result = new Audit();

		result.setProperty(property);

		return result;
	}

	public Collection<Audit> findAll() {
		return auditRepository.findAll();
	}

	public Audit findOne(int id_audit) {
		return auditRepository.findOne(id_audit);

	}

	public Audit save(Audit audit) {
		Audit result;
		Assert.notNull(audit);
		Auditor auditor = auditorService.findByPrincipal();
		Assert.isTrue(auditor.equals(audit.getAuditor()));

		result = auditRepository.save(audit);
		return result;
	}

	public void delete(Audit audit) {
		Assert.notNull(audit);
		Auditor auditor = auditorService.findByPrincipal();
		Assert.isTrue(auditor.equals(audit.getAuditor()));

		auditRepository.delete(audit);
	}

	//Other business methods

}
