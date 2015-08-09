package mobi.qubits.ex.library.domain;

import org.axonframework.commandhandling.CommandExecutionException;

/**
 * 
 * @author yizhuan
 *
 */
public class BorrowingSameBookException extends CommandExecutionException {

	public BorrowingSameBookException() {
		super("You are borrowing the same book you've laready borrowed.", null);
	}
	
	public BorrowingSameBookException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}	

}
