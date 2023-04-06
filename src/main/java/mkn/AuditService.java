package mkn;

import javax.persistence.EntityManager;

import java.util.List;

import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/*
 * This class can be used to query the Audit data in addition to using the RevisionRepository 
 * or another custom Repository can be created and added to Repository interfaces as needed.
 * Look at EnversRevisionRepositoryImpl for examples of how RevisionRepository works.
 */
@Service
@AllArgsConstructor
public class AuditService {
	private final EntityManager entityManager;
	
	@Transactional
	public List<Object> find(final Integer revisionId) {
//		https://vladmihalcea.com/the-best-way-to-implement-an-audit-log-using-hibernate-envers/
//		https://thoughts-on-java.org/hibernate-envers-query-data-audit-log/
		
//		Class.forName("	com.example.demo.Pizza"); 
//		AuditReaderFactory.get(entityManager) ...
		
		return AuditReaderFactory.get(entityManager).getCrossTypeRevisionChangesReader().findEntities(revisionId);
	}
}
