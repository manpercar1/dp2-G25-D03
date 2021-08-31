package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;

@Controller
@RequestMapping("/manageracc/task/")
public class ManagerTaskController extends AbstractController<Manager, Task>{
	
	@Autowired
	protected ManagerTaskListService listService;
	
	@Autowired
	protected ManagerTaskShowService showService;
	
	@Autowired
	protected ManagerTaskCreateService createService;
	
	@Autowired
	protected ManagerTaskUpdateService updateService;
	
	@Autowired
	protected ManagerTaskDeleteService deleteService;
	
	@Autowired
	protected ManagerTaskListSortedByExecutionPeriodService listExecutionPeriodService;
	
	@Autowired
	protected ManagerTaskListSortedByWorkload listWorkloadService;
	
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
