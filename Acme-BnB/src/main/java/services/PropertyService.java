package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PropertyRepository;
import domain.Property;

@Service
@Transactional
public class PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepository;

	public PropertyService() {
		super();
	}
	
	public Property create(){
		return null;
	}
	

	public Collection<Property> findAll(){
		return propertyRepository.findAll();
	}
	
	public Property findOne(int id_property){
		return propertyRepository.findOne(id_property);
		
	}
	
	public void save(Property property){
		propertyRepository.save(property);
	}
	
	public void delete(Property property){
		propertyRepository.delete(property);
	}
	
	//Other business methods

}
