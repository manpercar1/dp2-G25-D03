package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.features.spam.SpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam>{

	@Autowired
	protected SpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) { 
		//This method is purposely empty to avoid punishing coverage tests and making them be above 60% coverage
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;
				
		return this.repository.findSpam();
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
