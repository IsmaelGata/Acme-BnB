package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BookRepository;
import domain.Book;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public BookService() {
		super();
	}
	
	public Book create(){
		return null;
	}
	

	public Collection<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book findOne(int id_book){
		return bookRepository.findOne(id_book);
		
	}
	
	public void save(Book book){
		bookRepository.save(book);
	}
	
	public void delete(Book book){
		bookRepository.delete(book);
	}
	
	//Other business methods

}
