package mkn;

import static java.util.Arrays.asList;
import static org.eclipse.collections.impl.list.mutable.FastList.newListWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaService {
	private final PizzaRepository repository;
	private final PizzaTypeRepository typeRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public Pizza create(final String orderedBy, final String type) {
		final PizzaType pizzaType = typeRepository.findByName(type).orElseGet(() -> typeRepository.save(new PizzaType().setName(type)));
		final Pizza pizza = repository.save(new Pizza().setOrderedBy(orderedBy).setType(pizzaType));
		applicationEventPublisher.publishEvent(new PizzaCreatedEvent(pizza.getId()));
		return pizza;
	}

	@Transactional
	public void delete(final Long id) {
		repository.deleteById(id);
		applicationEventPublisher.publishEvent(new PizzaDeletedEvent(id));
	}

	@Transactional
	public void update(final Long id, final String type) {
		final PizzaType pizzaType = typeRepository.findByName(type).orElseGet(() -> typeRepository.save(new PizzaType().setName(type)));
		repository.save(repository.findById(id).get().setType(pizzaType));
	}
	
	@Transactional
	public List<Pizza> createMany(final String orderedBy, final String... types) {
		return newListWith(types).collect(type -> {
			final PizzaType pizzaType = typeRepository.findByName(type).orElseGet(() -> typeRepository.save(new PizzaType().setName(type)));
			return repository.save(new Pizza().setOrderedBy(orderedBy).setType(pizzaType));
		});
	}
}
