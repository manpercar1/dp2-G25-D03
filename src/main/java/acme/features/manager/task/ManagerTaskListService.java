package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerTaskListService implements AbstractListService<Manager, Task>{

	@Autowired
	protected ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {

		assert request != null;
		
		if(request.getModel().hasAttribute("id")) {
			return request.getModel().getInteger("id").equals(request.getPrincipal().getAccountId());
		}else {
			return true;
		}
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title",  "description", "info");

	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {

		assert request != null;
		Principal principal;
		principal = request.getPrincipal();
		return this.repository.findAllTaskById(principal.getAccountId());
	}
}
