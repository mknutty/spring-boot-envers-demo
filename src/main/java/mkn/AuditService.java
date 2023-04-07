package mkn;

import javax.persistence.EntityManager;

import java.util.List;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/*
 * This class can be used to query the Audit data in addition to using the RevisionRepository 
 * or another custom Repository can be created and added to Repository interfaces as needed.
 * Look at EnversRevisionRepositoryImpl for examples of how RevisionRepository works.
 * 
 * For more information
 * https://vladmihalcea.com/the-best-way-to-implement-an-audit-log-using-hibernate-envers/
 * https://thoughts-on-java.org/hibernate-envers-query-data-audit-log/
 */
@Service
@AllArgsConstructor
public class AuditService {
	private final EntityManager entityManager;
	
	@Transactional
	public List<Object> findEntities(final Integer revisionId) {
		return AuditReaderFactory.get(entityManager).getCrossTypeRevisionChangesReader().findEntities(revisionId);
	}
	
	/*
	 * Info on what this returns - https://stackoverflow.com/a/51029351
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> forRevisionsOfEntityWithChanges(final Class<?> clazz, final Long id) {
		return AuditReaderFactory.get(entityManager).createQuery()
				  .forRevisionsOfEntityWithChanges(clazz, false )
				  .add(AuditEntity.id().eq(id))
				  .getResultList();
	}
}
