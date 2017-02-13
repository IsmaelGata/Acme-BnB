package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditRepository;
import domain.Audit;

@Service
@Transactional
public class AuditService {
	
	@Autowired
	private AuditRepository auditRepository;

	public AuditService() {
		super();
	}
	
	public Audit create(){
		return null;
	}
	

	public Collection<Audit> findAll(){
		return auditRepository.findAll();
	}
	
	public Audit findOne(int id_audit){
		return auditRepository.findOne(id_audit);
		
	}
	
	public void save(Audit audit){
		auditRepository.save(audit);
	}
	
	public void delete(Audit audit){
		auditRepository.delete(audit);
	}
	
	//Other business methods

}
