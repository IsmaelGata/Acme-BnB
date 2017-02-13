package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ExtraAttributeRepository;
import domain.ExtraAttribute;

@Component
@Transactional
public class StringToExtraAttributeConverter implements Converter<String, ExtraAttribute>{
	
	@Autowired
	private ExtraAttributeRepository extraAttributeRepository;
	
	@Override
	public ExtraAttribute convert(String text) {
		ExtraAttribute result;
		int id;
		
		try {
			if(StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = extraAttributeRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
