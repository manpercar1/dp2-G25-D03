/*
 * EmployerApplicationLIstTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.task;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorTaskListSortedByExecutionPeriodService extends AcmePlannerTest {

	
	// Se comprueba que las tareas persistentes en la base de datos son los correctos
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/list_by_execution_period.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String info, final String workload, final String isFinished, final String newFinished, final String isPrivate, final String newStatus) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Tasks", "My tasks");
		
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
		super.checkInputBoxHasValue("isFinished", isFinished);
		super.checkInputBoxHasValue("newFinished", newFinished);
		super.checkInputBoxHasValue("isPrivate", isPrivate);
		super.checkInputBoxHasValue("newStatus", newStatus);
		
		super.clickOnReturnButton("Return");

		
		super.signOut();
	}
	
	// Se comprueba que un administrador no puede listar las tareas de otro
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/list_by_execution_period_negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(11)
	public void listNegative(final String id) {
		super.signIn("administrator2", "administrator2");
		this.driver.get("http://localhost:8050/Acme-Planner/administrator/task/list-sorted-by-execution-period?language=en&debug=true&id=" + id);
		super.checkErrorsExist();
	}
	
	
}
