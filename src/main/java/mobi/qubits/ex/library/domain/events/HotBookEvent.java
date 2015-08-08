package mobi.qubits.ex.library.domain.events;

import java.io.Serializable;

/**
 * 
 * @author yizhuan
 *
 */
public class HotBookEvent implements Serializable{

	private final String id;

	public HotBookEvent(String id) {
		super();
		this.id = id;

	}


	public String getId() {
		return id;
	}


	
}
