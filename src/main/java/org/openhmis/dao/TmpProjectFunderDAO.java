package org.openhmis.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openhmis.domain.TmpProjectFunder;

public class TmpProjectFunderDAO extends BaseDAO {

	// default constructor
	public TmpProjectFunderDAO() { }

	public TmpProjectFunder getTmpProjectFunderById(Integer funderId)  {
		String queryString = "select projectFunder " + 
			"from TmpProjectFunder as projectFunder " + 
			"where projectFunder.funderId =:funderId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("funderId", funderId);
		queryObject.setMaxResults(1);
		
		List<TmpProjectFunder> results = queryObject.list();
		session.close();
		
		if(results.size() > 0)
			return (TmpProjectFunder)results.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectFunder> getTmpProjectFunders() {
		String queryString = "select projectFunder " + 
				"from TmpProjectFunder as projectFunder";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		List<TmpProjectFunder> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectFunder> getTmpProjectFunders(Date updateDate) {
		String queryString = "select projectFunder " + 
				"from TmpProjectFunder as projectFunder " + 
				"where projectFunder.dateUpdated >= :updatedSince";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("updatedSince", updateDate);
		List<TmpProjectFunder> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectFunder> getTmpProjectFundersByProjectId(Integer projectId) {
		String queryString = "select projectFunder " + 
				"from TmpProjectFunder as projectFunder " + 
				"where projectFunder.projectId =:projectId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectId", projectId);
		List<TmpProjectFunder> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectFunder> getTmpProjectFundersByProjectId(Integer projectId, Date updateDate) {
		String queryString = "select projectFunder " + 
				"from TmpProjectFunder as projectFunder " + 
				"where projectFunder.projectId =:projectId " + 
				"  and projectFunder.dateUpdated >= :updatedSince";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectId", projectId);
		queryObject.setParameter("updatedSince", updateDate);
		List<TmpProjectFunder> results = queryObject.list();
		session.close();
		return results;
	}
}