package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTests extends AcmePlannerTest{


	// Se comprueba que se crean todas las tareas rellenando todos los campos sin errors(se dejan vacíos aquellos que pueden ser vacios)
	// y luego mostrando los detalles de cada tarea
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String info, final String newFinished, final String newStatus) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startExecution", startExecution);
		super.fillInputBoxIn("endExecution", endExecution);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("newStatus", newStatus);
		super.fillInputBoxIn("newFinished", newFinished);
		super.clickOnSubmitButton("Submit");

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
		super.checkInputBoxHasValue("isFinished", newFinished);
		super.checkInputBoxHasValue("isPrivate", newStatus);

		super.signOut();
	}
	
	// Se comprueba que no se crean las tareas que contienen errores
	// Restricciones violadas: campos nulos, fechas que están en el pasado, fecha final antes que la inicial,inserccion de texto en campos donde debe ir una fecha,
	//	números negativos o que no cumplan el formato, campos donde se deben insertar una url pero se inserta un texto, campos con palabras spam(titulo y descripcion).
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String workload, final String info, final String newFinished, final String newStatus) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startExecution", startExecution);
		super.fillInputBoxIn("endExecution", endExecution);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("newStatus", newStatus);
		super.fillInputBoxIn("newFinished", newFinished);
		super.clickOnSubmitButton("Submit");
	}
}
