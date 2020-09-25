package mkn;

import org.hibernate.envers.RevisionListener;

public class DemoRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		DemoCustomRevision customRevision = (DemoCustomRevision) revisionEntity;
		
		// Use this next line with Spring Security
		//customRevision.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		customRevision.setUserName("pizza-user");
	}

}
