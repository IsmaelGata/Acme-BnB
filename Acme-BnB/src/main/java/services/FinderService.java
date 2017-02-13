package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {
	
	@Autowired
	private FinderRepository finderRepository;

	public FinderService() {
		super();
	}
	
	public Finder create(){
		return null;
	}
	

	public Collection<Finder> findAll(){
		return finderRepository.findAll();
	}
	
	public Finder findOne(int id_finder){
		return finderRepository.findOne(id_finder);
		
	}
	
	public void save(Finder finder){
		finderRepository.save(finder);
	}
	
	public void delete(Finder finder){
		finderRepository.delete(finder);
	}
	
	//Other business methods

}
