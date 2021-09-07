package acme.features.manager.task;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.tasks.Task;
import acme.features.spam.SpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task>{
	
	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	protected SpamRepository spamRepository;

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
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "startExecution","endExecution","info","workload","isPrivate");
	}

	@Override
	public Task findOne(final Request<Task> request) {

		return this.repository.findOneTaskById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {

		//Falta VALIDAR SPAM -----
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean notSpamTitle = this.esSpam(entity.getTitle());
		final boolean notSpamDescription = this.esSpam(entity.getDescription());

		errors.state(request, !notSpamTitle, "title", "anonymous.shout.error.text");
		errors.state(request, !notSpamDescription, "description", "anonymous.shout.error.text");
		
		errors.state(request, entity.getStartExecution() != null && this.checkAno(entity.getStartExecution()), "startExecution", "administrator.task.error.fecha");
        errors.state(request, entity.getEndExecution() != null && this.checkAno(entity.getEndExecution()), "endExecution", "administrator.task.error.fecha");
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {

		assert request != null;
		assert entity != null;
		
		if(request.getModel().getString("newStatus").equals("true")) {
			entity.setIsPrivate(Boolean.TRUE);
		}else if(request.getModel().getString("newStatus").equals("false")){
			entity.setIsPrivate(Boolean.FALSE);
		}
		
		if(request.getModel().getString("newFinished").equals("true")) {
			entity.setIsFinished(Boolean.TRUE);
		}else if(request.getModel().getString("newFinished").equals("false")){
			entity.setIsFinished(Boolean.FALSE);
		}
		this.repository.save(entity);
	}
	
	public boolean esSpam(final String text) {
		
		final Spam spamObject = this.spamRepository.findSpam();
		
		final List<String> spamWords = Arrays.asList(spamObject.getWords().split(", "));
		
		final String[] taskWords = text.toLowerCase().split(" ");
		
		Double numberSpamWords = 0.;
		Double spamPercentaje;
		final Double length = (double) taskWords.length;
		
		for(final String word:taskWords) {
			final String cleanWord = word.replaceAll("(?![À-ÿa-zA-Z0-9]).", "");
			if(spamWords.contains(cleanWord)) {
				numberSpamWords++;
			}
		}
		
		spamPercentaje = ((numberSpamWords/length)*100);
		if(spamPercentaje>spamObject.getThreshold()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean checkAno(final Date fecha) {
        boolean result = true;
        final String ano = String.valueOf(fecha.getYear());
        if(ano.length() > 4) {
            result = false;
        }

        return result;
    }

}
