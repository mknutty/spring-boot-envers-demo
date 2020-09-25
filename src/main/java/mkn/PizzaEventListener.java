package mkn;

import java.util.Optional;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class PizzaEventListener {
	private PizzaRepository repository;

	@TransactionalEventListener
	public void processPizzaCreatedEvent(PizzaCreatedEvent event) {
		Optional<Revision<Integer, Pizza>> lastChangeRevision = repository.findLastChangeRevision(event.getId());
		Integer revisionNumber = lastChangeRevision.get().getRequiredRevisionNumber();
    log.info("*** Pizza created - Revision Number: " + revisionNumber + " " + event);
		event.setRevisionId(revisionNumber);
		// Things like publishing event to kafka can occur in this method
	}  
	
	@TransactionalEventListener
  public void processPizzaUpdatedEvent(PizzaUpdatedEvent event) {
    Optional<Revision<Integer, Pizza>> lastChangeRevision = repository.findLastChangeRevision(event.getId());
    Integer revisionNumber = lastChangeRevision.get().getRequiredRevisionNumber();
    log.info("*** Pizza updated - Revision Number: " + revisionNumber + " " + event);
    event.setRevisionId(revisionNumber);
    // Things like publishing event to kafka can occur in this method
  } 
	
	@TransactionalEventListener
	public void processPizzaDeletedEvent(PizzaDeletedEvent event) {
		log.info("*** Pizza deleted - ID: " + event.getId());
		// Things like publishing event to kafka can occur in this method
	}
}
