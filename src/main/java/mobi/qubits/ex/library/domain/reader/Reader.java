package mobi.qubits.ex.library.domain.reader;

import java.util.ArrayList;
import java.util.List;

import mobi.qubits.ex.library.domain.commands.RegisterNewReaderCommand;
import mobi.qubits.ex.library.domain.events.BorrowEvent;
import mobi.qubits.ex.library.domain.events.NewReaderRegisteredEvent;
import mobi.qubits.ex.library.domain.events.ReturnEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * 
 * @author yizhuan
 *
 */
public class Reader extends AbstractAnnotatedAggregateRoot<String> {
	
	@AggregateIdentifier
	private String id;

	private String name;
	
	private int booksBorrowed = 0;
	
	private List<String> borrowedBookIds = new ArrayList<String>();

	Reader() {

	}

	@CommandHandler
	public Reader(RegisterNewReaderCommand cmd) {
		apply(new NewReaderRegisteredEvent(cmd.getId(),cmd.getName()));
	}	

	public int getBooksBorrowed() {
		return booksBorrowed;
	}

	public void increaseBooksBorrowed() {
		this.booksBorrowed++;
	}
	
	public boolean hasBook(String bookId){
		return borrowedBookIds.contains(bookId);
	}
	
	public void borrowBook(String bookId){
		apply(new BorrowEvent(id, bookId));
	}

	public void returnBook(String bookId){
		apply(new ReturnEvent(id, bookId));
	}
	
	@EventSourcingHandler
	void on(NewReaderRegisteredEvent event) {
		this.id = event.getId();
		this.name = event.getName();
	}	
	
	
	@EventSourcingHandler
	void on(BorrowEvent event) {
		booksBorrowed++;
		borrowedBookIds.add(event.getBookId());
	}	


	@EventSourcingHandler
	void on(ReturnEvent event) {
		booksBorrowed--;
	}

}
