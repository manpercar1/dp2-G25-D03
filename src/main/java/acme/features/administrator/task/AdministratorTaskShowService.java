package acme.features.administrator.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorTaskShowService implements AbstractShowService<Administrator, Task>{

	@Autowired
	protected AdministratorTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {

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
	public void unbind(final Request<Task> request, final Task entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "startExecution","endExecution","info","workload","isPrivate","isFinished");
	}

	@Override
	public Task findOne(final Request<Task> request) {

		assert request != null;
		return this.repository.findOneTaskById(request.getModel().getInteger("id"));
	}

}
