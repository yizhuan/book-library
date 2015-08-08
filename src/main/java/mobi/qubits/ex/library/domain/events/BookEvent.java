package mobi.qubits.ex.library.domain.events;

import java.io.Serializable;

/**
 * 
 * @author yizhuan
 *
 */
public interface BookEvent extends Serializable {
	public String getBorrowerId();
	public String getBookId();

}
