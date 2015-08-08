package mobi.qubits.ex.library.domain;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author yizhuan
 *
 */
public class BookAdminSaga extends AbstractAnnotatedSaga{

		private static final long serialVersionUID = -5929174026616232734L;

		/*
		@Autowired
	    private transient CommandGateway commandGateway;

	    @StartSaga
	    @SagaEventHandler(associationProperty = "borrowerId")
	    public void handle(BookBorrowedEvent event) {
	    	
	    	System.out.println();
	    	
	    }
	    */

}
