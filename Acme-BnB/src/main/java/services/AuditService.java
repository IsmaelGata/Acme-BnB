
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.AuditRepository;
import domain.Audit;
import domain.Auditor;
import domain.Property;
import form.AuditForm;

@Service
@Transactional
public class AuditService {

	//Managed repository

	@Autowired
	private AuditRepository	auditRepository;

	//Supported services

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private PropertyService	propertyService;


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

	public Audit reconstruct(AuditForm auditForm, BindingResult binding) {
		Audit result;

		Property property = propertyService.findOne(auditForm.getIdProperty());

		if (auditForm.getId() != 0) {
			result = findOne(auditForm.getId());
			result.setAttachments(auditForm.getAttachments());
			result.setDraft(auditForm.isDraft());
			result.setMoment(new Date(System.currentTimeMillis() - 10));
			result.setText(auditForm.getText());

		} else {
			result = create(property);
			result.setAttachments(auditForm.getAttachments());
			result.setDraft(auditForm.isDraft());
			result.setMoment(new Date(System.currentTimeMillis() - 10));
			result.setText(auditForm.getText());
		}

		return result;
	}

	public AuditForm convertoToFormObject(Audit audit) {
		Assert.notNull(audit);
		AuditForm result = new AuditForm();
		result.setAttachments(audit.getAttachments());
		result.setDraft(audit.getDraft());
		result.setIdProperty(audit.getProperty().getId());
		result.setText(audit.getText());
		result.setId(audit.getId());

		return result;
	}

	public Integer countPropertyAuditor(int idAuditor, int idProperty) {
		return auditRepository.countPropertyAuditor(idAuditor, idProperty);
	}
}
