package mkn;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "REVISION")
@RevisionEntity(DemoRevisionListener.class)
@ToString
public class DemoCustomRevision extends DefaultRevisionEntity {
	private static final long serialVersionUID = -8821537464814522070L;
	
	@Column(length=200)
	@Getter @Setter private String userName;
	
	@ElementCollection(fetch = FetchType.EAGER)
  @JoinTable(name = "MODIFIED_ENTITIES", joinColumns = @JoinColumn(name = "REVISION_ID"))
  @Column(name = "ENTITY_NAME")
	@ModifiedEntityNames
	@Getter @Setter private Set<String> modifiedEntityNames;
}
