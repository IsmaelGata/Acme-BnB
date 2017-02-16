
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	//Managed repository
	@Autowired
	private CommentRepository	commentRepository;


	//Supported services

	//Constructor

	public CommentService() {
		super();
	}

	//Simple CRUD methods

	public Comment create() {
		Comment result = new Comment();
		return result;
	}

	public Collection<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Comment findOne(int id_comment) {
		return commentRepository.findOne(id_comment);

	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);
		Comment result = commentRepository.save(comment);

		return result;
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		commentRepository.delete(comment);
	}

	//Other business methods

}
