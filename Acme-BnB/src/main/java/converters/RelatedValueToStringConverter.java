package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RelatedValue;

@Component
@Transactional
public class RelatedValueToStringConverter implements Converter<RelatedValue, String>{
	
	@Override
	public String convert(RelatedValue relatedValue) {
		String result;
		
		if(relatedValue == null) {
			result = null;
		} else {
			result = String.valueOf(relatedValue.getId());
		}
	
		return result;
	}

}
