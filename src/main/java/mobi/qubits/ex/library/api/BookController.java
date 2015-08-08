package mobi.qubits.ex.library.api;

import java.util.List;

import javax.validation.Valid;

import mobi.qubits.ex.library.api.requests.BookRequest;
import mobi.qubits.ex.library.api.requests.RegisterBookRequest;
import mobi.qubits.ex.library.domain.commands.BorrowCommand;
import mobi.qubits.ex.library.domain.commands.RegisterNewBookCommand;
import mobi.qubits.ex.library.domain.commands.ReturnCommand;
import mobi.qubits.ex.library.query.BookEntry;
import mobi.qubits.ex.library.query.BookEntryRepository;
import mobi.qubits.ex.library.query.ReaderEntryRepository;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.DefaultIdentifierFactory;
import org.axonframework.domain.IdentifierFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author yizhuan
 *
 */
@RestController
public class BookController {

	private final IdentifierFactory identifierFactory = new DefaultIdentifierFactory();

	@Autowired
	private BookEntryRepository bookEntryRepository;

	@Autowired
	private ReaderEntryRepository readerEntryRepository;

	@Autowired
	private CommandGateway cmdGateway;
	
	@RequestMapping(value="/api/books", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> registerNewBook(@RequestBody @Valid RegisterBookRequest req, UriComponentsBuilder b) {
		String id = identifierFactory.generateIdentifier();
		cmdGateway.send(new RegisterNewBookCommand(id, req.getTitle(), req.getAuthor()));

		UriComponents uriComponents = b.path("/api/books/{id}").buildAndExpand(
				id);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponents.toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}	
	
	
	@RequestMapping(value = "/api/books", method = RequestMethod.GET)
	public @ResponseBody List<BookEntry> findAllBooks() {
		return bookEntryRepository.findAll();
	}	
	
	@RequestMapping(value = "/api/books/{id}", method = RequestMethod.GET)
	public @ResponseBody BookEntry findBook(@PathVariable String id) {
		return bookEntryRepository.findOne(id);
	}		
	
}
