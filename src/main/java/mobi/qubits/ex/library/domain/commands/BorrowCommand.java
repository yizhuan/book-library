package mobi.qubits.ex.library.domain.commands;

import mobi.qubits.ex.library.domain.reader.Reader;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * 
 * @author yizhuan
 *
 */
public class BorrowCommand {

	@TargetAggregateIdentifier
	private String borrowerId;
	
	private String bookId;
	
	public BorrowCommand(String borrowerId, String bookId) {
		super();
		this.bookId = bookId;
	
		this.borrowerId = borrowerId;
	}


	public String getBookId() {
		return bookId;
	}


	public String getBorrowerId() {
		return borrowerId;
	}

	
}
