package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExtraAttributeRepository;
import domain.ExtraAttribute;

@Service
@Transactional
public class ExtraAttributeService {
	
	@Autowired
	private ExtraAttributeRepository extraAttributeRepository;

	public ExtraAttributeService() {
		super();
	}
	
	public ExtraAttribute create(){
		return null;
	}
	

	public Collection<ExtraAttribute> findAll(){
		return extraAttributeRepository.findAll();
	}
	
	public ExtraAttribute findOne(int id_extraAttribute){
		return extraAttributeRepository.findOne(id_extraAttribute);
		
	}
	
	public void save(ExtraAttribute extraAttribute){
		extraAttributeRepository.save(extraAttribute);
	}
	
	public void delete(ExtraAttribute extraAttribute){
		extraAttributeRepository.delete(extraAttribute);
	}
	
	//Other business methods

}
