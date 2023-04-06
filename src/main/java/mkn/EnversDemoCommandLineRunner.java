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
		
		pizzaRepository.findRevisions(1L).forEach(revision -> log.info("*** " + revision));
		
		final List<Pizza> createMany = pizzaService.createMany("Ralph", "Cheese", "Ham");
		
		final Optional<Revision<Integer, Pizza>> lastRevision = pizzaRepository.findLastChangeRevision(createMany.get(0).getId());
		auditService.find(lastRevision.get().getRevisionNumber().get()).forEach(System.out::println);
	
	}

}
