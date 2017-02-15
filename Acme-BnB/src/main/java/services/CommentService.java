
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;


	public CommentService() {
		super();
	}

	public Comment create() {
		return null;
	}

	public Collection<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Comment findOne(int id_comment) {
		return commentRepository.findOne(id_comment);

	}

	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}

	//Other business methods

}
