package org.openhmis.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openhmis.domain.TmpProjectContinuum;

public class TmpProjectContinuumDAO extends BaseDAO {

	// default constructor
	public TmpProjectContinuumDAO() { }

	public TmpProjectContinuum getTmpProjectContinuumById(Integer projectCocId)  {
		String queryString = "select projectContinuum " + 
			"from TmpProjectContinuum as projectContinuum " + 
			"where projectContinuum.projectCocId =:projectCocId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectCocId", projectCocId);
		queryObject.setMaxResults(1);
		
		List<TmpProjectContinuum> results = queryObject.list();
		session.close();
		
		if(results.size() > 0)
			return (TmpProjectContinuum)results.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectContinuum> getTmpProjectContinuums() {
		String queryString = "select projectContinuum " + 
				"from TmpProjectContinuum as projectContinuum";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		List<TmpProjectContinuum> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectContinuum> getTmpProjectContinuums(Date updateDate) {
		String queryString = "select projectContinuum " + 
				"from TmpProjectContinuum as projectContinuum " + 
				"where projectContinuum.dateUpdated >= :updatedSince";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("updatedSince", updateDate);
		List<TmpProjectContinuum> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectContinuum> getTmpProjectContinuumsByProjectId(Integer projectId) {
		String queryString = "select projectContinuum " + 
				"from TmpProjectContinuum as projectContinuum " + 
				"where projectContinuum.projectId =:projectId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectId", projectId);
		List<TmpProjectContinuum> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectContinuum> getTmpProjectContinuumsByProjectId(Integer projectId, Date updateDate) {
		String queryString = "select projectContinuum " + 
				"from TmpProjectContinuum as projectContinuum " + 
				"where projectContinuum.projectId =:projectId " + 
				"  and projectContinuum.dateUpdataed >= :updatedSince";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectId", projectId);
		queryObject.setParameter("updatedSince", updateDate);
		List<TmpProjectContinuum> results = queryObject.list();
		session.close();
		return results;
	}
}