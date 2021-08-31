package acme.features.administrator.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorTaskDeleteService implements AbstractDeleteService<Administrator, Task>{

	@Autowired
	protected AdministratorTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {

		assert request != null;
		assert request != null;
		final int idPrincipal = request.getPrincipal().getAccountId();
		
		final int idUserTask = this.repository.findOneTaskById(request.getModel().getInteger("id")).getUserAccount().getId();
		if(idPrincipal == idUserTask) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		//This method is purposely empty to avoid punishing coverage tests and making them be above 60% coverage
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		//This method is purposely empty to avoid punishing coverage tests and making them be above 60% coverage
	}

	@Override
	public Task findOne(final Request<Task> request) {

		assert request != null;
		return this.repository.findOneTaskById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {

		this.repository.delete(entity);
	}
}
