
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ComentableRepository;
import domain.Comment;

@Service
@Transactional
public class ComentableService {

	//Managed repository

	@Autowired
	private ComentableRepository	comentableRepository;


	//Supported services

	//Constructor

	public ComentableService() {
		super();
	}

	//Simple CRUD methods

	//Other business methods

	public Collection<Comment> getComments(int comentableId) {
		return comentableRepository.getComments(comentableId);
	}

}
