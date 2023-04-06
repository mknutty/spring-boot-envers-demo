package mkn;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaTypeRepository extends JpaRepository<PizzaType, Long> {

	Optional<PizzaType> findByName(String name);

}
