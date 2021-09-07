package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTests extends AcmePlannerTest{
	
	// Se comprueba que se actualizan los datos ya persistentes en la base de datos con valores sin errores.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updatePositive(final int recordIndex, final String title, final String description, final String info, final String startExecution, final String endExecution, final String workload, final String newStatus, final String newFinished,
								final String newTitle, final String newDescription, final String newInfo) {
	
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, info);
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", newTitle);
		super.fillInputBoxIn("description", newDescription);
		super.fillInputBoxIn("startExecution", startExecution);
		super.fillInputBoxIn("endExecution", endExecution);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("info", newInfo);
		super.fillInputBoxIn("newStatus", newStatus);
		super.fillInputBoxIn("newFinished", newFinished);
		super.clickOnSubmitButton("Update");

		super.clickOnMenu("Manager", "My tasks");

		super.checkColumnHasValue(recordIndex, 0, newTitle);
		super.checkColumnHasValue(recordIndex, 1, newDescription);
		super.checkColumnHasValue(recordIndex, 2, newInfo);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", newTitle);
		super.checkInputBoxHasValue("description", newDescription);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("info", newInfo);
		super.checkInputBoxHasValue("isFinished", newFinished);
		super.checkInputBoxHasValue("isPrivate", newStatus);

		super.signOut();
		
	}
	

	//	Se comprueba que no se actualizan tareas donde se hayan insertado valores con errores.
	//	Restricciones violadas: campos nulos, fechas que están en el pasado, fecha final antes que la inicial,inserccion de texto en campos donde debe ir una fecha,
	//	números negativos o que no cumplan el formato, campos donde se deben insertar una url pero se inserta un texto, campos con palabras spam(titulo y descripcion).
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateNegative(final int recordIndex, final String title, final String description, final String info, final String startExecution, final String endExecution, final String workload,final String status,final String finished,
								final String newTitle, final String newDescription, final String newInfo,final String newStartExecution,final String newEndExecution, final String newWorkload, final String newStatus, final String newFinished) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "My tasks");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, info);

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", newTitle);
		super.fillInputBoxIn("description", newDescription);
		super.fillInputBoxIn("startExecution", newStartExecution);
		super.fillInputBoxIn("endExecution", newEndExecution);
		super.fillInputBoxIn("workload", newWorkload);
		super.fillInputBoxIn("info", newInfo);
		super.fillInputBoxIn("newStatus", newStatus);
		super.fillInputBoxIn("newFinished", newFinished);
		super.clickOnSubmitButton("Update");

		super.clickOnMenu("Manager", "My tasks");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, info);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("isFinished", finished);
		super.checkInputBoxHasValue("isPrivate", status);

		super.signOut();
	}	
	
	// Se comprueba que un manager no puede actualizar tareas pertenecientes a otro manager
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-two-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void updateNegativeTwo(final String id) {
		super.signIn("manager2", "manager2");
		this.driver.get("http://localhost:8050/Acme-Work-Plans/manageracc/task/update?language=en&debug=true&id=" + id);
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
