package mobi.qubits.ex.library.domain.events;

/**
 * 
 * @author yizhuan
 *
 */
public class ReturnEvent implements BookEvent{

	private final String borrowerId;
	private final String bookId;
	

	public ReturnEvent(String borrowerId, String bookId) {
		super();
		this.borrowerId = borrowerId;
		this.bookId = bookId;
	}
	
	public String getBorrowerId() {
		return borrowerId;
	}
	public String getBookId() {
		return bookId;
	}

}
