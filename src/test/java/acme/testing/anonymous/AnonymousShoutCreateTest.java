package acme.testing.anonymous;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{
	
	//Se comprueba que se crean todos los shouts con todas sus variantes posibles (en este caso con una URL de info vacía) por parte de un anónimo 
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/shout/create-positive.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(10)
	public void createShoutPositive(final int recordIndex,final String author, final String text, final String info) {
		super.clickOnMenu("Anonymous", "Create shout");
		
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}
	/*
	Se comprueba que se no crean todos los shouts con todos los posibles errores que podrían contener: campos vacíos o campos con palabras spam "sex"
	superando el umbral del 10%
	 */
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/shout/create-negative.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(20)
	public void createShoutNegative(final int recordIndex,final String author, final String text, final String info) {
		super.clickOnMenu("Anonymous", "Create shout");
		
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
}
