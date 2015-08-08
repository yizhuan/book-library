package mobi.qubits.ex.library;

import mobi.qubits.ex.library.domain.book.Book;
import mobi.qubits.ex.library.domain.commands.BorrowCommand;
import mobi.qubits.ex.library.domain.commands.RegisterNewBookCommand;
import mobi.qubits.ex.library.domain.commands.ReturnCommand;
import mobi.qubits.ex.library.domain.events.BorrowEvent;
import mobi.qubits.ex.library.domain.events.NewBookRegisteredEvent;
import mobi.qubits.ex.library.domain.events.NewReaderRegisteredEvent;
import mobi.qubits.ex.library.domain.events.ReturnEvent;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author yizhuan
 *
 */
public class BookTest {

	private FixtureConfiguration<Book> fixture;

	@Before
	public void setUp() {
		fixture = Fixtures.newGivenWhenThenFixture(Book.class);
	}

	@Test
	public void testRegisterBook() {

		fixture.given()
				.when(new RegisterNewBookCommand("1", "Book1", "Albert"))
				.expectEvents(
						new NewBookRegisteredEvent("1", "Book1", "Albert"));

	}


}
