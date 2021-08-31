package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListAndShowTests extends AcmePlannerTest{
	//Se comprueba el listado y el visionado de cada tarea individual logueado como un autentificado
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(10)
	public void listTask(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String finished) {
		super.signIn("authenticated1", "authenticated1");
		
		super.clickOnMenu("Account", "List tasks");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, startExecution);
		super.checkColumnHasValue(recordIndex, 3, endExecution);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		
		super.clickOnReturnButton("Return");
		
		super.signOut();
	}
	
	//Se comprueba el listado con el orden de las ID y posteriormente se comprueba el visionado de cada tarea
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(11)
	public void listTaskSortById(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String finished) {
		super.signIn("authenticated1", "authenticated1");
		
		super.clickOnMenu("Account", "List tasks");
		super.clickOnReturnButton("Sort by ID");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, startExecution);
		super.checkColumnHasValue(recordIndex, 3, endExecution);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		
		super.clickOnReturnButton("Return");
		
		super.signOut();
	}
	//Se comprueba el listado con el orden de los periodo de ejecucion y posteriormente se comprueba  el visionado de cada tarea
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list-order-execution-period.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(12)
	public void listTaskSortByExecution(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String finished) {
		super.signIn("authenticated1", "authenticated1");
		
		super.clickOnMenu("Account", "List tasks");
		super.clickOnReturnButton("Sort by execution period");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, startExecution);
		super.checkColumnHasValue(recordIndex, 3, endExecution);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		
		super.clickOnReturnButton("Return");
		
		super.signOut();
	}
	
	//Se comprueba el listado con el orden de los workloads y posteriormente se comprueba  el visionado de cada tarea
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(13)
	public void listTaskSortByWorkload(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String finished) {
		super.signIn("authenticated1", "authenticated1");
		
		super.clickOnMenu("Account", "List tasks");
		super.clickOnReturnButton("Sort by workload");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, startExecution);
		super.checkColumnHasValue(recordIndex, 3, endExecution);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		
		super.clickOnReturnButton("Return");
		
		super.signOut();
	}
	//Se comprueba que no se pueden listar las tareas con el ID de otro usuario
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(14)
	public void listNegative(final String id) {
		super.signIn("authenticated1", "authenticated1");
		this.driver.get("http://localhost:8050/Acme-Planner/authenticated/task/list?language=en&debug=true&" + id);
		super.checkErrorsExist();
	}
	
	//Se comprueba que no se pueden listar las tareas ordenadas por el periodo de ejecución con el ID de otro usuario
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(15)
	public void listNegativeSortByExecution(final String id) {
		super.signIn("authenticated1", "authenticated1");
		this.driver.get("http://localhost:8050/Acme-Planner/authenticated/task/list-sorted-by-execution-period?language=en&debug=true&" + id);
		super.checkErrorsExist();
	}
	
	//Se comprueba que no se pueden listar las tareas ordenadas por el workload con el ID de otro usuario
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(16)
	public void listNegativeSortByWorkload(final String id) {
		super.signIn("authenticated1", "authenticated1");
		this.driver.get("http://localhost:8050/Acme-Planner/authenticated/task/list-sorted-by-workload?language=en&debug=true&" + id);
		super.checkErrorsExist();
	}

	//Se comprueba que no se puede acceder a una tarea que no existe
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/show-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(17)
	public void showNegative(final String id) {
		super.signIn("authenticated1", "authenticated1");
		this.driver.get("http://localhost:8050/Acme-Planner/authenticated/task/show?" + id + "&language=en&debug=true&");
		super.checkErrorsExist();
	}
}
