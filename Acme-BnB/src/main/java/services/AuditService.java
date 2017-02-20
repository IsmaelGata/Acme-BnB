
package services;

import java.util.Collection;
import java.util.Date;

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
		Auditor auditor = auditorService.findByPrincipal();

		result.setAuditor(auditor);
		result.setProperty(property);

		return result;
	}

	public Collection<Audit> findAll() {
		return auditRepository.findAll();
	}

	public Audit findOne(int auditId) {
		return auditRepository.findOne(auditId);

	}

	public Audit save(Audit audit) {
		Audit result;
		Assert.notNull(audit);
		Auditor auditor = auditorService.findByPrincipal();
		Assert.isTrue(auditor.equals(audit.getAuditor()));

		if (audit.getId() != 0) {
			// FIXME ¿No seria mejor setearlo a true en lugar de hacer un Assert?
			//TODO R: no, yo quiero comprobar que realmente es un borrador. Supongamos que mediante hacking
			//		  se intenta modificar un ya emitido, automaticamente se convierte en borrador.
			Assert.isTrue(audit.getDraft() == true);
		}

		audit.setMoment(new Date());
		result = auditRepository.save(audit);
		return result;
	}

	public void delete(Audit audit) {
		Assert.notNull(audit);
		Auditor auditor = auditorService.findByPrincipal();
		Assert.isTrue(auditor.equals(audit.getAuditor()));
		Assert.isTrue(audit.getDraft());

		auditRepository.delete(audit);
	}

	//Other business methods

}
