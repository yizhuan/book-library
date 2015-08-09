package mobi.qubits.ex.library.domain;

import org.axonframework.commandhandling.CommandExecutionException;

/**
 * 
 * @author yizhuan
 *
 */
public class BookAlreadyTakenException extends CommandExecutionException {
	
	public BookAlreadyTakenException() {
		super("This book is not available. It has been taken by another reader.", null);
	}
	
	public BookAlreadyTakenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}	

}
