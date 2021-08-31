package acme.testing.administrator.dashboardTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowServiceTests extends AcmePlannerTest {
	
	//Se comprueba que los valores que proporciona el dashboard son superiores a 0
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/dashboardTasks/show-positive.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(10)
	public void showDashboardPositive(final String dummy) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Tasks Dashboard");
		final List<WebElement> elements = this.driver.findElements(By.xpath("//td"));
		
		for(int i=0; i<elements.size(); i++) {
			assert Double.parseDouble(elements.get(i).getText()) >= 0.;
		}
		super.signOut();
		
	}
	
	//Se comprueba que los valores que proporciona el dashboard son los esperados
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/dashboardTasks/show-positive-two.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(20)
	public void showDashboardPositiveTwo(final String totalPublic, final String totalPrivate, final String totalFinished,final String totalNonFinished,
		final String averageExecutionPeriod, final String deviationExecutionPeriod, final String minimunExecutionPeriod, final String maximunExecutionPeriod, 
		final String averageWorkloads, final String deviationWorkload, final String minimunWorkload, final String maximunWorkload) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Tasks Dashboard");
		final List<WebElement> elements = this.driver.findElements(By.xpath("//td"));
		
		final List<String> parameters=new ArrayList<>(Arrays.asList(totalPublic,totalPrivate,totalFinished,totalNonFinished,
			averageExecutionPeriod,deviationExecutionPeriod,minimunExecutionPeriod,
			maximunExecutionPeriod,averageWorkloads,deviationWorkload,minimunWorkload,maximunWorkload));
		
		for(int i=0; i<elements.size(); i++) {
			assert elements.get(i).getText().equals(parameters.get(i));
		}
		super.signOut();
		
	}
	
	
	//Se comprueba que solo puede acceder al dashboard un administrador
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/dashboardTasks/show-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(30)
	public void showDashboardNegative(final String credentialsUsername, final String credentialsPassword) {
		
		if(credentialsUsername.contains("anonymous")) {
			this.driver.get("http://localhost:8050/Acme-Planner/administrator/dashboard/show?language=en&debug=true");
			super.checkErrorsExist();
		}else {
			super.signIn(credentialsUsername, credentialsPassword);
			this.driver.get("http://localhost:8050/Acme-Planner/administrator/dashboard/show?language=en&debug=true");
			super.checkErrorsExist();
			super.signOut();
		}
	}
	
	
	


}
