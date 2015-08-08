package mobi.qubits.ex.library.domain.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * 
 * @author yizhuan
 *
 */
public class RegisterNewBookCommand {
	
	@TargetAggregateIdentifier
	private final String id;
	
	private String title;
	private String author;
	
	public RegisterNewBookCommand(String id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		
	}


	public String getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return author;
	}

}
