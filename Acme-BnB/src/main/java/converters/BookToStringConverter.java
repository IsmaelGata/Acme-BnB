package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Book;

@Component
@Transactional
public class BookToStringConverter implements Converter<Book, String>{
	
	@Override
	public String convert(Book book) {
		String result;
		
		if(book == null) {
			result = null;
		} else {
			result = String.valueOf(book.getId());
		}
	
		return result;
	}

}
