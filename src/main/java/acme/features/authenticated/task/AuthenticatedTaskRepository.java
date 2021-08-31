package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select t from Task t where t.isPrivate = false and t.isFinished=true")
	Collection<Task> findPublicAndFinishedTask();
	
	@Query("SELECT t FROM Task t WHERE t.isPrivate = false AND t.isFinished=true ORDER BY t.workload asc")
	Collection<Task> findTasksOrderByWorkload();
}
