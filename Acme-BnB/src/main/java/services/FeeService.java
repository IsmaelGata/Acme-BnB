package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FeeRepository;
import domain.Fee;

@Service
@Transactional
public class FeeService {
	
	@Autowired
	private FeeRepository feeRepository;

	public FeeService() {
		super();
	}
	
	public Fee create(){
		return null;
	}
	

	public Collection<Fee> findAll(){
		return feeRepository.findAll();
	}
	
	public Fee findOne(int feeId){
		return feeRepository.findOne(feeId);
		
	}
	
	public void save(Fee fee){
		feeRepository.save(fee);
	}
	
	public void delete(Fee fee){
		feeRepository.delete(fee);
	}
	
	//Other business methods

}
