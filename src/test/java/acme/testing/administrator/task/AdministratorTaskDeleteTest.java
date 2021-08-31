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

public class AdministratorTaskDeleteTest extends AcmePlannerTest {

	// Comprobamos que una task se elimina correctamente
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void delete(final int recordIndex, final String title, final String description, final String info) {		
		
		//Iniciamos sesión
		super.signIn("administrator", "administrator");
		
		//Accedemos a la lista de tasks
		super.clickOnMenu("Tasks", "My tasks");		
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, info);
		
		//Accedemos a una task
		super.clickOnListingRecord(recordIndex);
		
		//Obetenemos la url de la task que vamos a borrar
		final String urlTaskBorrada = super.driver.getCurrentUrl();
		
		//Borramos la task
		super.clickOnSubmitButton("Delete");
		
		//Intentamos acceder a la task borrada y comprobamos si hay errores
		this.driver.get(urlTaskBorrada);
		
		super.checkErrorsExist();
		
		//Cerramos sesión
		super.signOut();
	}
	
	
	// Comprobamos que un manager no puede borrar las tareas asociadas a otro manager
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegative(final String id) {
		super.signIn("administrator2", "administrator2");

		this.driver.get("http://localhost:8050/Acme-Planner/administrator/task/delete?language=en&debug=true&id=" + id);
		super.checkErrorsExist();

		super.signOut();
	}
	

}
