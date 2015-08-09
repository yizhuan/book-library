package mobi.qubits.ex.library.domain;

import mobi.qubits.ex.library.domain.book.Book;
import mobi.qubits.ex.library.domain.commands.BorrowCommand;
import mobi.qubits.ex.library.domain.commands.ReturnCommand;
import mobi.qubits.ex.library.domain.reader.Reader;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author yizhuan
 *
 */
@Component
public class BookAdminCommandHandler {
	
	@Autowired
	private Repository<Book> bookRepository;
	
	@Autowired
	private Repository<Reader> readerRepository;
	
	@CommandHandler
	public void on(BorrowCommand cmd) throws BookAlreadyTakenException, BorrowingSameBookException, MaxAllowanceExceededException{
		
		Book book = bookRepository.load(cmd.getBookId());		
		Reader reader = readerRepository.load(cmd.getBorrowerId());
		
		if (book.isBorrowed()){
			//the book is taken (not currently available)			
			throw new BookAlreadyTakenException();			
		}
		
		if (reader.hasBook(cmd.getBookId())){			
			throw new BorrowingSameBookException();
		}
		
		int booksBorrowed = reader.getBooksBorrowed();
		
		if (booksBorrowed>=3){			
			 throw new MaxAllowanceExceededException();
		}
		
		reader.borrowBook(cmd.getBookId());
		
		book.lendBook(cmd.getBorrowerId());
		
		int pop = book.getPopularityCount();
		
		if (pop==5){
			book.rateBookHot();
		}
				
	}
		
	@CommandHandler
	public void on(ReturnCommand cmd){
		Reader reader = readerRepository.load(cmd.getBorrowerId());
		reader.returnBook(cmd.getBookId());
	}

}
