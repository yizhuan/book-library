package mobi.qubits.ex.library.domain;

import org.axonframework.commandhandling.CommandExecutionException;

/**
 * 
 * @author yizhuan
 *
 */
public class MaxAllowanceExceededException extends CommandExecutionException {

	public MaxAllowanceExceededException() {
		super("Each reader can only borrow up to 3 books.", null);
	}
	
	public MaxAllowanceExceededException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}	

}
