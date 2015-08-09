package mobi.qubits.ex.library;

import mobi.qubits.ex.library.domain.reader.Reader;

import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
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
public class ReaderConfiguration {
	
	@Autowired
	AxonConfiguration axonconf;
	
	@Bean
	public EventSourcingRepository<Reader> readerRepository() {

		DefaultMongoTemplate template = new DefaultMongoTemplate(axonconf.mongo);
		MongoEventStore eventStore = new MongoEventStore(template
				);
		EventSourcingRepository<Reader> repository = new EventSourcingRepository<Reader>(
				Reader.class, eventStore);
		repository.setEventBus(axonconf.eventBus());
		return repository;
	}	

	@Bean
	public AggregateAnnotationCommandHandler<Reader> readerCommandHandler() {
		AggregateAnnotationCommandHandler<Reader> commandHandler = AggregateAnnotationCommandHandler
				.subscribe(Reader.class, readerRepository(), axonconf.commandBus());
		return commandHandler;
	}
			
	
}
