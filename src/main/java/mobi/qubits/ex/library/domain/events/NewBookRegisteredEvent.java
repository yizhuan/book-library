package mobi.qubits.ex.library.domain.events;

import java.io.Serializable;

/**
 * 
 * @author yizhuan
 *
 */
public class NewBookRegisteredEvent implements Serializable{
	
	private final String id;
	
	private String title;
	private String author;
	
	public NewBookRegisteredEvent(String id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		
	}


	public String getBorrowerId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return author;
	}

}
