package acme.features.anonymous.task;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select t from Task t where t.isPrivate = false and t.isFinished = false ORDER BY t.workload ASC")
	Collection<Task> findNonFinishedTasks();
	
	@Query("select t from Task t where t.isPrivate = false and t.isFinished = false")
	List<Task> findAllNonFinishedTask();
}
