package mobi.qubits.ex.library;

import mobi.qubits.ex.library.domain.book.Book;
import mobi.qubits.ex.library.domain.commands.BorrowCommand;
import mobi.qubits.ex.library.domain.commands.RegisterNewReaderCommand;
import mobi.qubits.ex.library.domain.commands.ReturnCommand;
import mobi.qubits.ex.library.domain.events.BorrowEvent;
import mobi.qubits.ex.library.domain.events.NewBookRegisteredEvent;
import mobi.qubits.ex.library.domain.events.NewReaderRegisteredEvent;
import mobi.qubits.ex.library.domain.events.ReturnEvent;
import mobi.qubits.ex.library.domain.reader.Reader;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author yizhuan
 *
 */
public class ReaderTest {

	private FixtureConfiguration<Reader> fixture;

	@Before
	public void setUp() {
		fixture = Fixtures.newGivenWhenThenFixture(Reader.class);
	}
	
	@Test
	public void testRegisterReader() {

		fixture.given()
				.when(new RegisterNewReaderCommand("1", "John"))
				.expectEvents(
						new NewReaderRegisteredEvent("1", "John"));

	}
	
	/*
	@Test
	public void testBorrowBook() {
		
		fixture.given(new NewBookRegisteredEvent("1", "Book100", "Albert"),
				new NewReaderRegisteredEvent("1", "John")
						)
				.when(new BorrowCommand("1", "1"))
				.expectEvents(new BorrowEvent("1", "1"));

	}
	
	
	@Test
	public void testReturnBook() {
		
		fixture.given(
				new NewBookRegisteredEvent("1", "Book100", "Albert"),
				new NewReaderRegisteredEvent("1", "John"),
				new BorrowEvent("1", "1")
				)				
				.when(new ReturnCommand("1", "1"))
				.expectEvents(new ReturnEvent("1", "1"));

	}
	*/	
	

}
