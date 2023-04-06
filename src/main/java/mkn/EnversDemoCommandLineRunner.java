package mkn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class EnversDemoCommandLineRunner implements CommandLineRunner {
	private final PizzaService pizzaService;
	private final PizzaRepository pizzaRepository;
	
	@Override
	public void run(final String... args) throws Exception {
		final Long id = pizzaService.create("Fred", "Supreme").getId();
		pizzaService.create("Barney", "Margarita");
		pizzaService.update(id, "Meat Lovers");		
		pizzaService.delete(id);
		
		pizzaRepository.findRevisions(1L).forEach(revision -> log.info("*** " + revision));
		
		pizzaService.createMany("Ralph", "Cheese", "Ham");
	}

}
