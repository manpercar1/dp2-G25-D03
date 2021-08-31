package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowTest  extends AcmePlannerTest {
	
	//Se comprueba el listado de las tareas públicas y no finalizadas, con un usuario anónimo
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/task/show.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(10)
	public void showTask(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String info, final String workload) {
		
		//primero nos logueamos como administrador
		super.signIn("administrator", "administrator");
		
		//luego creamos una task que NO sea privada
		super.clickOnMenu("Tasks", "Create task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startExecution", startExecution);
		super.fillInputBoxIn("endExecution", endExecution);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("newFinished", "false");
		super.fillInputBoxIn("newStatus", "false");
		super.clickOnSubmitButton("Submit");
		super.clickOnMenu("Tasks", "My tasks");
		
		//nos deslogueamos
		super.signOut();
		
		//accedemos a la lista de tasks
		super.clickOnMenu("Anonymous", "List tasks");
		
		//accedemos a una task en concreto
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startExecution", startExecution);
		super.checkInputBoxHasValue("endExecution", endExecution);
		super.checkInputBoxHasValue("workload", workload);
		
		
		
	}
	
	//Se comprueba que un usuario anónimo no puede mostrar las tareas de un administrador
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/task/show-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(11)
	public void showTaskNegative(final int id) {
		
		this.driver.get("http://localhost:8050/Acme-Planner/administrator/task/list?language=en&debug=true&id=" + id);
		super.checkErrorsExist();
		
	}

}
