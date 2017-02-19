
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import form.CommentForm;

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

	public CommentForm create() {
		CommentForm result = new CommentForm();

		return result;
	}

	public Collection<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Comment findOne(int commentId) {
		return commentRepository.findOne(commentId);

	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);
		Comment result = commentRepository.save(comment);

		return result;
	}

	//Other business methods

	public Comment reconstruct(CommentForm commentForm) {
		Comment result = new Comment();

		result.setStars(commentForm.getStars());
		result.setText(commentForm.getText());
		result.setTitle(commentForm.getTitle());

		if (commentForm.getId() != 0) {
			result.setId(commentForm.getId());
		}

		return result;
	}

	public CommentForm conversionToFormObject(Comment comment) {
		Assert.notNull(comment);

		CommentForm result = new CommentForm();
		result.setStars(comment.getStars());
		result.setText(comment.getText());
		result.setTitle(comment.getTitle());
		result.setId(comment.getId());

		return result;
	}
}
