package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RelatedValueRepository;
import domain.RelatedValue;

@Component
@Transactional
public class StringToRelatedValueConverter implements Converter<String, RelatedValue>{
	
	@Autowired
	private RelatedValueRepository relatedValueRepository;
	
	@Override
	public RelatedValue convert(String text) {
		RelatedValue result;
		int id;
		
		try {
			if(StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = relatedValueRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
