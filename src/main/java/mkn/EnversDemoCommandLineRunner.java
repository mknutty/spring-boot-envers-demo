package mkn;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class EnversDemoCommandLineRunner implements CommandLineRunner {
	private final PizzaService pizzaService;
	private final PizzaRepository pizzaRepository;
	private final AuditService auditService;
	
	@Override
	public void run(final String... args) throws Exception {
		final Long id = pizzaService.create("Fred", "Supreme").getId();
		pizzaService.create("Barney", "Margarita");
		pizzaService.update(id, "Meat Lovers");		
		pizzaService.delete(id);
		
		pizzaRepository.findRevisions(id).forEach(revision -> log.info("+++ " + revision));
		auditService.forRevisionsOfEntityWithChanges(Pizza.class, id)
			.forEach(revision -> { 
				log.info("--- Entity " + revision[0]);
				log.info("--- Change " + revision[2]);
				log.info("--- Fields " + revision[3]);
			});
		
		final List<Pizza> createMany = pizzaService.createMany("Ralph", "Cheese", "Meat Lovers");
		
		final Optional<Revision<Integer, Pizza>> lastRevision = pizzaRepository.findLastChangeRevision(createMany.get(0).getId());
		auditService.findEntities(lastRevision.get().getRevisionNumber().get()).forEach(entity -> log.info("!!! " + entity));
	
	}

}
