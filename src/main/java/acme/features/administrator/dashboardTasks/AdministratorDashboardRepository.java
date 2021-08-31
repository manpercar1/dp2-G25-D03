package acme.features.administrator.dashboardTasks;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select count(t) from Task t where t.isPrivate = false")
	Integer totalPublicTasks();
	
	@Query("select count(t) from Task t where t.isPrivate = true")
	Integer totalPrivateTasks();
	
	@Query("select count(t) from Task t where t.endExecution < ?1")
	Integer totalFinishedTasks(Date now);
	
	@Query("select count(t) from Task t where t.endExecution > ?1")
	Integer totalNonFinishedTasks(Date now);
	
	@Query("select t from Task t")
	Collection<Task> allTasks();
	
	@Query("select workload from Task t")
	Collection<BigDecimal> allWorkloads();

	@Query("select min(t.workload) from Task t")
	Double minimunTaskWorkload();
	
	@Query("select max(t.workload) from Task t")
	Double maximumTaskWorkload();
	
}

