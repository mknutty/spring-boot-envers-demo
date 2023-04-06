package mkn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaService {
	private final PizzaRepository repository;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public Pizza create(final String orderedBy, final String type) {
		final Pizza pizza = repository.save(new Pizza().setOrderedBy(orderedBy).setType(type));
		applicationEventPublisher.publishEvent(new PizzaCreatedEvent(pizza.getId()));
		return pizza;
	}

	@Transactional
	public void delete(final Long id) {
		repository.deleteById(id);
		applicationEventPublisher.publishEvent(new PizzaDeletedEvent(id));
	}

	public void update(final Long id, final String type) {
		repository.save(repository.findById(id).get().setType(type));

	}
	
	@Transactional
	public List<Pizza> create2(final String orderedBy, final String... types) {
		final List<Pizza> pizzas = new ArrayList<>();
		Arrays.asList(types).forEach(type -> {
			final Pizza pizza = repository.save(new Pizza().setOrderedBy(orderedBy).setType(type));
			pizzas.add(pizza);
		});
		
		return pizzas;
	}
}
