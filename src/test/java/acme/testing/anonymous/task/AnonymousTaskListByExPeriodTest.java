package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListByExPeriodTest extends AcmePlannerTest {
	
	//Se comprueba el listado de las tareas públicas y no finalizadas ordenadas por periodo de ejecución, con un usuario anónimo
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/task/list.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(11)
	public void listTaskByExPeriod(final int recordIndex, final String title, final String description, final String startExecution, final String endExecution, final String info, final String workload) {
		
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
		
		//listamos las tasks como usuario anónimo
		super.clickOnMenu("Anonymous", "List tasks");
		
		//hacemos click en el boton de ordenarlas por periodo de ejecución
		super.clickOnReturnButton("Order by execution period");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, startExecution);
		super.checkColumnHasValue(recordIndex, 3, endExecution);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, "false");
		
	}
	
	//Se comprueba que un usuario anónimo no puede listar las tareas de un administrador
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/task/list.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(12)
	public void listTaskNegative() {
		
		this.driver.get("http://localhost:8050/Acme-Planner/administrator/task/list");
		super.checkErrorsExist();
		
	}

}
