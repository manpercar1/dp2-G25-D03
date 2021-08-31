package acme.testing.anonymous;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class AnonymousShoutListTest extends AcmePlannerTest{

	//Se comprueba que los shouts ya persistentes son los correctos
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/shout/list.csv", encoding= "utf-8", numLinesToSkip= 1)
	@Order(11)
	public void listAll(final int recordIndex,final String author, final String text) {
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}
}
