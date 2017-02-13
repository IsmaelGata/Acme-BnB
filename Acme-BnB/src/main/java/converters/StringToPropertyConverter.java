package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PropertyRepository;
import domain.Property;

@Component
@Transactional
public class StringToPropertyConverter implements Converter<String, Property>{
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Override
	public Property convert(String text) {
		Property result;
		int id;
		
		try {
			if(StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = propertyRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
