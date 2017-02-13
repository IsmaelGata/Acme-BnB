package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.TenantRepository;
import domain.Tenant;

@Component
@Transactional
public class StringToTenantConverter implements Converter<String, Tenant>{
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Override
	public Tenant convert(String text) {
		Tenant result;
		int id;
		
		try {
			if(StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = tenantRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
