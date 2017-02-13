package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Tenant;

@Component
@Transactional
public class TenantToStringConverter implements Converter<Tenant, String>{
	
	@Override
	public String convert(Tenant tenant) {
		String result;
		
		if(tenant == null) {
			result = null;
		} else {
			result = String.valueOf(tenant.getId());
		}
	
		return result;
	}

}
