package acme.testing.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class SpamShowTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	
	// Test cases -------------------------------------------------------------
	
	//Se comprueba que el spam ya persistente es correcto
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/show-positive.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(10)
	public void showPositive(final String threshold, final String words) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Spam", "Spam list");
		
		super.checkInputBoxHasValue("threshold", threshold);
		super.checkInputBoxHasValue("words", words);
		
		super.signOut();
		
		
	}
	
	//Se comprueba que los datos de la base de datos y los parametrizados no coinciden
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/show-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(20)
	public void showNegative(final String threshold, final String words) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Spam", "Spam list");
		
		final WebElement thresholdElement = super.locateOne(By.name("threshold"));
		final WebElement wordsElement = super.locateOne(By.name("words"));
		final String currentThreshold=thresholdElement.getAttribute("value");
		final String currentWords=wordsElement.getAttribute("value");
		
		assert !currentThreshold.equals(threshold) || (!currentWords.equals(words));
		
		super.signOut();
				
	}
	
	//Se comprueba que los datos solo pueden ser listados por el administrador
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/notAuthorised-show.csv", encoding= "utf-8", numLinesToSkip=1)
	@Order(30)
	public void showNotAuthorised(final String credentialsUsername, final String credentialsPassword) {
		
		if(credentialsUsername.contains("anonymous")) {
			this.driver.get("http://localhost:8050/Acme-Planner/administrator/spam/show?language=en&debug=true");
			super.checkErrorsExist();
		}else {
			super.signIn(credentialsUsername, credentialsPassword);
			this.driver.get("http://localhost:8050/Acme-Planner/administrator/spam/show?language=en&debug=true");
			super.checkErrorsExist();
			super.signOut();
		}
				
	}
}
