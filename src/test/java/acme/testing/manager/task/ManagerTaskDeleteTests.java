package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTests extends AcmePlannerTest{

	// Comprobamos que se borran correctamente las tareas asociadas a un manager
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deletePositive(final int recordIndex, final String title, final String description, final String info) {
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, info);
		
		super.clickOnListingRecord(recordIndex);
		final String urlTaskDeleted = this.driver.getCurrentUrl();
		super.clickOnSubmitButton("Delete");
		
		this.driver.get(urlTaskDeleted);
		super.checkErrorsExist();
		
	}
	
	// Comprobamos que un manager no puede borrar las tareas asociadas a otro manager
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegative(final String id) {
		super.signIn("manager2", "manager2");
		
		this.driver.get("http://localhost:8050/Acme-Planner/manageracc/task/delete?language=en&debug=true&id=" + id);
		super.checkErrorsExist();
		
		super.signOut();
	}
}
