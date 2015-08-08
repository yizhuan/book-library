package mobi.qubits.ex.library.domain.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * 
 * @author yizhuan
 *
 */
public class ReturnCommand {

	@TargetAggregateIdentifier
	private String borrowerId;
	
	private String bookId;

	public ReturnCommand(String borrowerId, String bookId) {
		super();
		this.borrowerId = borrowerId;
		this.bookId = bookId;	
	}


	public String getBorrowerId() {
		return borrowerId;
	}

	public String getBookId(){
		return this.bookId;
	}

}
