package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.InvoiceRepository;
import domain.Invoice;

@Component
@Transactional
public class StringToInvoiceConverter implements Converter<String, Invoice>{
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public Invoice convert(String text) {
		Invoice result;
		int id;
		
		try {
			if(StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = invoiceRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
