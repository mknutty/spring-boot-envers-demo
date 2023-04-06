package mkn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class EnversDemoCommandLineRunner implements CommandLineRunner {
	private PizzaService pizzaService;
	private PizzaRepository pizzaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		pizzaService.create("Fred", "Supreme");
		pizzaService.create("Barney", "Margarita");
		pizzaService.update(1L, "Meat Lovers");		
		pizzaService.delete(1L);
		
		pizzaRepository.findRevisions(1L).forEach(revision -> log.info("*** " + revision));
		
		pizzaService.create2("Ralph", "Cheese", "Ham");
	}

}
