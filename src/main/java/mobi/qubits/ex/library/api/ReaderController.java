package mobi.qubits.ex.library.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import mobi.qubits.ex.library.api.requests.BookRequest;
import mobi.qubits.ex.library.api.requests.RegisterReaderRequest;
import mobi.qubits.ex.library.domain.BookAlreadyTakenException;
import mobi.qubits.ex.library.domain.BorrowingSameBookException;
import mobi.qubits.ex.library.domain.MaxAllowanceExceededException;
import mobi.qubits.ex.library.domain.commands.BorrowCommand;
import mobi.qubits.ex.library.domain.commands.RegisterNewReaderCommand;
import mobi.qubits.ex.library.domain.commands.ReturnCommand;
import mobi.qubits.ex.library.query.BookEntryRepository;
import mobi.qubits.ex.library.query.ReaderEntry;
import mobi.qubits.ex.library.query.ReaderEntryRepository;

import org.axonframework.commandhandling.CommandExecutionException;
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
public class ReaderController {

	private final IdentifierFactory identifierFactory = new DefaultIdentifierFactory();

	@Autowired
	private BookEntryRepository bookEntryRepository;

	@Autowired
	private ReaderEntryRepository readerEntryRepository;

	@Autowired
	private CommandGateway cmdGateway;

	@RequestMapping(value = "/api/readers", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> registerNewReader(
			@RequestBody @Valid RegisterReaderRequest req, UriComponentsBuilder b) {
		String id = identifierFactory.generateIdentifier();
		cmdGateway.send(new RegisterNewReaderCommand(id, req.getName()));

		UriComponents uriComponents = b.path("/api/readers/{id}")
				.buildAndExpand(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponents.toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/readers", method = RequestMethod.GET)
	public @ResponseBody List<ReaderEntry> findAllReaders() {
		return readerEntryRepository.findAll();
	}	
	
	@RequestMapping(value = "/api/readers/{id}", method = RequestMethod.GET)
	public @ResponseBody ReaderEntry findReader(@PathVariable String id) {
		return readerEntryRepository.findOne(id);
	}	
	
	
	@RequestMapping(value = "/api/readers/{id}/borrow", method = RequestMethod.POST)

	public ResponseEntity<?> borrowBook(@RequestBody @Valid BookRequest req, @PathVariable String id) {
		//cmdGateway.send(new BorrowCommand(id, req.getBookId()));
		/*
		try {
			cmdGateway.sendAndWait(new BorrowCommand(id, req.getBookId()), 3000, TimeUnit.MILLISECONDS);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (BookAlreadyTakenException e) {
			e.printStackTrace();
			String err = "{\"error\":\""+e.getLocalizedMessage() + "\"}";
			return new ResponseEntity<String>(err, HttpStatus.BAD_REQUEST);			
		}
		catch (BorrowingSameBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String err = "{\"error\":\""+e.getLocalizedMessage() + "\"}";
			return new ResponseEntity<String>(err, HttpStatus.BAD_REQUEST);			
		}
		catch (MaxAllowanceExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String err = "{\"error\":\""+e.getLocalizedMessage() + "\"}";
			return new ResponseEntity<String>(err, HttpStatus.BAD_REQUEST);						
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		*/
		
		try {
			cmdGateway.sendAndWait(new BorrowCommand(id, req.getBookId()), 3000, TimeUnit.MILLISECONDS);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (CommandExecutionException ex) {
			
			//e.printStackTrace();
			
			Throwable e = ex.getCause();
			
			if (e instanceof BookAlreadyTakenException){
				String err = "{\"error\":\""+e.getLocalizedMessage() + "\"}";
				return new ResponseEntity<String>(err, HttpStatus.BAD_REQUEST);
			} 
			else if (e instanceof BorrowingSameBookException){
				String err = "{\"error\":\""+e.getLocalizedMessage() + "\"}";
				return new ResponseEntity<String>(err, HttpStatus.BAD_REQUEST);		
			} else if (e instanceof MaxAllowanceExceededException){
				String err = "{\"error\":\""+e.getLocalizedMessage() + "\"}";
				return new ResponseEntity<String>(err, HttpStatus.BAD_REQUEST);			
			} 
			else {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@RequestMapping(value = "/api/readers/{id}/return", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void returnBook(@RequestBody @Valid BookRequest req, @PathVariable String id) {
		cmdGateway.send(new ReturnCommand(id, req.getBookId()));
	}	

	

}
