package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.InvoiceRepository;
import domain.Invoice;

@Service
@Transactional
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	public InvoiceService() {
		super();
	}
	
	public Invoice create(){
		return null;
	}
	

	public Collection<Invoice> findAll(){
		return invoiceRepository.findAll();
	}
	
	public Invoice findOne(int id_invoice){
		return invoiceRepository.findOne(id_invoice);
		
	}
	
	public void save(Invoice invoice){
		invoiceRepository.save(invoice);
	}
	
	public void delete(Invoice invoice){
		invoiceRepository.delete(invoice);
	}
	
	//Other business methods

}
