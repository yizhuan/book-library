package mobi.qubits.ex.library;

import mobi.qubits.ex.library.domain.BookCommandGateway;
import mobi.qubits.ex.library.domain.book.Book;

import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.gateway.GatewayProxyFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author yizhuan
 *
 */
@Configuration
@Import(AxonConfiguration.class)
public class BookConfiguration {
	@Autowired
	AxonConfiguration axonconf;

	
	@Bean
	public EventSourcingRepository<Book> bookRepository() {

		DefaultMongoTemplate template = new DefaultMongoTemplate(axonconf.mongo);
		MongoEventStore eventStore = new MongoEventStore(template
				);
		EventSourcingRepository<Book> repository = new EventSourcingRepository<Book>(
				Book.class, eventStore);
		repository.setEventBus(axonconf.eventBus());
		return repository;
	}	

	@Bean
	public AggregateAnnotationCommandHandler<Book> bookCommandHandler() {
		AggregateAnnotationCommandHandler<Book> commandHandler = AggregateAnnotationCommandHandler
				.subscribe(Book.class, bookRepository(), axonconf.commandBus());
		return commandHandler;
	}
		
	
	@Bean
	public CommandGatewayFactoryBean<BookCommandGateway> bookCommandGatewayFactoryBean() {
		CommandGatewayFactoryBean<BookCommandGateway> factory = new CommandGatewayFactoryBean<BookCommandGateway>();
		factory.setGatewayInterface(BookCommandGateway.class);
		factory.setCommandBus(axonconf.commandBus());
		return factory;
	}	
		
}
