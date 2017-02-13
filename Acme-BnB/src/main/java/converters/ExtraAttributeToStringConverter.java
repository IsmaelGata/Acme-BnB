package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ExtraAttribute;

@Component
@Transactional
public class ExtraAttributeToStringConverter implements Converter<ExtraAttribute, String>{
	
	@Override
	public String convert(ExtraAttribute extraAttribute) {
		String result;
		
		if(extraAttribute == null) {
			result = null;
		} else {
			result = String.valueOf(extraAttribute.getId());
		}
	
		return result;
	}

}
