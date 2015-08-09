package mobi.qubits.ex.library.domain;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mobi.qubits.ex.library.domain.commands.BorrowCommand;

import org.axonframework.commandhandling.gateway.Timeout;
import org.axonframework.common.annotation.MetaData;

/**
 * 
 * @author yizhuan
 *
 */
public interface BookCommandGateway {

	void send(BorrowCommand command) throws BookAlreadyTakenException,
			BorrowingSameBookException, MaxAllowanceExceededException;

	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void sendAndWait(BorrowCommand command, @MetaData("bookId") String bookId)
			throws BookAlreadyTakenException, BorrowingSameBookException,
			MaxAllowanceExceededException;

	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void sendAndWait(BorrowCommand command) throws TimeoutException,
			InterruptedException, BookAlreadyTakenException,
			BorrowingSameBookException, MaxAllowanceExceededException;

	void sendAndWait(BorrowCommand command, long timeout, TimeUnit unit)
			throws TimeoutException, InterruptedException,
			BookAlreadyTakenException, BorrowingSameBookException,
			MaxAllowanceExceededException;
}
