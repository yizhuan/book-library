package mobi.qubits.ex.library.domain.events;


/**
 * 
 * @author yizhuan
 *
 */
public class BorrowEvent implements BookEvent{

	private final String borrowerId;//reader
	
	private String bookId;

	public BorrowEvent(String id,  String bookId) {
		super();
		this.borrowerId = id;
		
		this.bookId = bookId;
	}


	public String getBorrowerId() {
		return borrowerId;
	}


	public String getBookId() {
		return bookId;
	}
	
}
