package mobi.qubits.ex.library;

import mobi.qubits.ex.library.domain.book.Book;

import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
		
	
}
