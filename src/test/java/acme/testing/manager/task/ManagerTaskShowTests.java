package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowTests extends AcmePlannerTest{

		// Se comprueba que un manager no puede ver los detalles de las tareas de otro manager
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void showNegative(final String id) {
			super.signIn("manager2", "manager2");
			this.driver.get("http://localhost:8050/Acme-Planner/manageracc/task/show?language=en&debug=true&id=" + id);
			super.checkErrorsExist();
			
			super.signOut();
		}
		
		// El test positivo de show se comprueba en los test de listar al mirar los detalles
		// de una tarea comprobando que son correctos respecto a los datos que le pasamos
}
