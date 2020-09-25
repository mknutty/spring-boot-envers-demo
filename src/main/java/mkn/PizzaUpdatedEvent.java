package mkn;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PizzaUpdatedEvent {
  
  @NonNull
	private Long id;
	
	@Setter
	private Integer revisionId;
}
