package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/task/")
public class AuthenticatedTaskController extends AbstractController<Authenticated,Task>{

	@Autowired
	protected AuthenticatedTaskListService listService;
	
	@Autowired
	protected AuthenticatedTaskShowService showService;
	
	@Autowired
	protected AuthenticatedTaskListSortedByExecutionPeriodService listExecutionPeriodService;

	@Autowired
	protected AuthenticatedTaskListSortedByWorkloadService listWorkloadService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_SORTED_BY_EXECUTION_PERIOD, BasicCommand.LIST, this.listExecutionPeriodService);
		super.addCustomCommand(CustomCommand.LIST_SORTED_BY_WORKLOAD, BasicCommand.LIST, this.listWorkloadService);
	}
}
