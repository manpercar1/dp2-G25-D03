package acme.features.administrator.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.userAccount.id = ?1")
	Collection<Task> findAllTaskById(int id);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
	
	@Query("SELECT t FROM Task t WHERE t.userAccount.id = ?1 ORDER BY t.workload asc")
	Collection<Task> findTasksOrderByWorkload(int id);
}
