package mobi.qubits.ex.library.domain.book;

import mobi.qubits.ex.library.domain.commands.RegisterNewBookCommand;
import mobi.qubits.ex.library.domain.events.HotBookEvent;
import mobi.qubits.ex.library.domain.events.LendEvent;
import mobi.qubits.ex.library.domain.events.NewBookRegisteredEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * 
 * @author yizhuan
 *
 */
public class Book extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = 4245371056740478404L;

	@AggregateIdentifier
	private String id;

	private String title;
	private String author;

	private Boolean isTaken = false;

	private Boolean isHot = false;

	private int popularityCount = 0;

	Book() {

	}

	@CommandHandler
	public Book(RegisterNewBookCommand cmd) {
		apply(new NewBookRegisteredEvent(cmd.getId(), cmd.getTitle(),
				cmd.getAuthor()));
	}	

	@EventSourcingHandler
	void on(NewBookRegisteredEvent event) {
		this.id = event.getBorrowerId();
		this.title = event.getTitle();
		this.author = event.getAuthor();
		this.isTaken = false;
		this.popularityCount = 0;

	}

	
	public void rateBookHot(){
		apply(new HotBookEvent(id));
	}

	@EventSourcingHandler
	void on(HotBookEvent event) {
		this.isHot = true;
	}
		
	public void lendBook(String borrowerId){
		apply(new LendEvent(borrowerId, id));
	}	
	
	@EventSourcingHandler
	void on(LendEvent event) {
		this.isTaken = true;
		this.popularityCount++;
	}
		
	public Boolean isBorrowed() {
		return isTaken;
	}	

	public Boolean isHot() {
		return isHot;
	}
	
	public int getPopularityCount() {
		return popularityCount;
	}
	
	
	
}
