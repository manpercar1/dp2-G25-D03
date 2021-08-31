package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListByExecutionPeriodTests extends AcmePlannerTest{

	// Se comprueba que las tareas persistentes en la base de datos(ordenadas por periodo de ejecucion ) son los correctos
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-by-execution-period-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listByExecutionPeriodPositive(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String info,final String finished, final String status) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "My tasks");
		super.clickOnReturnButton("Sort by execution period");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, info);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("isFinished", finished);
		super.checkInputBoxHasValue("isPrivate", status);

		super.signOut();
	}
	
	// Se comprueba que un manager no puede listar tareas ordenadas por periodo de ejecucion de otro manager
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-by-execution-period-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void listByExecutionPeriodNegative(final String id) {
		super.signIn("manager2", "manager2");
		this.driver.get("http://localhost:8050/Acme-Planner/manageracc/task/list-sorted-by-execution-period?language=en&debug=true&id=" + id);
		super.checkErrorsExist();
		
		super.signOut();
	}
}
