package mkn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long>, RevisionRepository<Pizza, Long, Integer> {

}
