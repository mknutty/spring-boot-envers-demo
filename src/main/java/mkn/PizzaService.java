package mkn;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaService {
	private PizzaRepository repository;
	private final ApplicationEventPublisher applicationEventPublisher;
	
	@Transactional
	public Pizza create(String orderedBy, String type) {
		Pizza pizza = repository.save(new Pizza().setOrderedBy(orderedBy).setType(type));
		applicationEventPublisher.publishEvent(new PizzaCreatedEvent(pizza.getId()));
		return pizza;
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
		applicationEventPublisher.publishEvent(new PizzaDeletedEvent(id ));
	}

  public void update(Long id, String type) {
    repository.save(repository.findById(id).get().setType(type));
    
  }
}
