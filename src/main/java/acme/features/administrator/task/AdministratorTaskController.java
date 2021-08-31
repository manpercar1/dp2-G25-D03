package acme.features.administrator.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/task/")
public class AdministratorTaskController extends AbstractController<Administrator, Task>{

	@Autowired
	protected AdministratorTaskListService listService;
	
	@Autowired
	protected AdministratorTaskShowService showService;
	
	@Autowired
	protected AdministratorTaskUpdateService updateService;
	
	@Autowired 
	protected AdministratorTaskCreateService createService;
	
	@Autowired
	protected AdministratorTaskDeleteService deleteService;
	
	@Autowired
	protected AdministratorTaskListSortedByExecutionPeriodService listExecutionPeriodService;
	
	@Autowired
	protected AdministratorTaskListSortedByWorkloadService listWorkloadService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.LIST_SORTED_BY_EXECUTION_PERIOD, BasicCommand.LIST, this.listExecutionPeriodService);
		super.addCustomCommand(CustomCommand.LIST_SORTED_BY_WORKLOAD, BasicCommand.LIST, this.listWorkloadService);
	}
}
