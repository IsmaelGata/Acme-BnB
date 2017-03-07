
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;
import domain.Lessor;
import domain.Tenant;
import form.CommentForm;

@Service
@Transactional
public class CommentService {

	//Managed repository
	@Autowired
	private CommentRepository	commentRepository;
	
	@Autowired
	private ActorService 		actorService;
	
	@Autowired
	private LessorService		lessorService;
	
	@Autowired
	private TenantService		tenantService;


	//Supported services

	//Constructor

	public CommentService() {
		super();
	}

	//Simple CRUD methods

	public CommentForm create(int comentableId, String comentableType) {
		CommentForm result = new CommentForm();
		
		result.setComentableId(comentableId);
		result.setComentableType(comentableType);
		
		if(result.getComentableId() == 0) {
			Actor principal = actorService.findByPrincipal();
			result.setComentableId(principal.getId());
		}
		
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
		Assert.isTrue(comment.getComentableType().equals("Lessor") || comment.getComentableType().equals("Tenant"));
		
		Actor principal = actorService.findByPrincipal();
		Lessor lessor = null;
		Tenant tenant = null;
		
		if (principal.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("LESSOR")) {
			lessor = lessorService.findByPrincipal();
			if(comment.getComentableId() == principal.getId()) {
				comment.setAuthor(lessor);
				comment.setComentableId(lessor.getId());
			} else if(comment.getComentableType().equals("Tenant")) {
				comment.setAuthor(lessor);
			} else {
				throw new IllegalArgumentException();
			}
		} else if(principal.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("TENANT")) {
			tenant = tenantService.findByPrincipal();
			if(comment.getComentableId() == principal.getId()) {
				comment.setAuthor(tenant);
				comment.setComentableId(tenant.getId());
			} else if(comment.getComentableType().equals("Lessor")) {
				comment.setAuthor(tenant);
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new IllegalArgumentException();
		}
		
		Comment result = commentRepository.save(comment);

		return result;
	}

	//Other business methods
	
	public Comment reconstruct(CommentForm commentForm) {
		Comment result = new Comment();

		result.setStars(commentForm.getStars());
		result.setText(commentForm.getText());
		result.setTitle(commentForm.getTitle());
		result.setId(commentForm.getId());
		result.setComentableId(commentForm.getComentableId());
		result.setComentableType(commentForm.getComentableType());
		result.setMoment(new Date());

		return result;
	}

	public CommentForm conversionToFormObject(Comment comment) {
		Assert.notNull(comment);

		CommentForm result = new CommentForm();
		result.setStars(comment.getStars());
		result.setText(comment.getText());
		result.setTitle(comment.getTitle());
		result.setId(comment.getId());
		result.setComentableId(comment.getComentableId());
		result.setComentableType(comment.getComentableType());

		return result;
	}
	
	public Collection<Comment> findByPrincipal() {
		Collection<Comment> result;
		Actor principal = actorService.findByPrincipal();
		result = commentRepository.findByActor(principal.getId());
		
		return result;
	}
	
	public Collection<Comment> findByLessor(int lessorId) {
		Collection<Comment> result;
		result = commentRepository.findByActor(lessorId);
		
		return result;
	}
	
	public Collection<Comment> findByTenant(int tenantId) {
		Collection<Comment> result;
		result = commentRepository.findByActor(tenantId);
		
		return result;
	}
}
