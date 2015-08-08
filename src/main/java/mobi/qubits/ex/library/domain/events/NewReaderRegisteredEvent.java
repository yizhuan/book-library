package mobi.qubits.ex.library.domain.events;

import java.io.Serializable;

/**
 * 
 * @author yizhuan
 *
 */
public class NewReaderRegisteredEvent implements Serializable{

	private String id;
	
	private String name;

	public NewReaderRegisteredEvent(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
	
}
